package com.sagalogistics.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.lib.database.UserDAO
import com.sagalogistics.lib.models.User
import com.sagalogistics.implementation.models.UserImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

//TODO documentation
class UserDAOFirebase : UserDAO { //temporary, will be changed after GoogleAuth integration
    override fun add(user: User) {
        val key = user.key
        if (key == null) {
            val pushedPostRef = usersRef.push()
            user.key = pushedPostRef.key
            pushedPostRef.setValue(user)
        } else {
            update(key, user) //reuse code
        }
    }

    override fun get(key: String): Future<User?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = usersRef.child(key).get()
            Tasks.await(task)
            if(task.isSuccessful) {
                val data = task.result
                val user: User? = data!!.getValue(UserImpl::class.java)
                if (user != null) {
                    user.key = data.key
                    return@Callable user
                }
            }
            return@Callable null
        })
    }

    override fun getAll(): Future<Set<User>?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = usersRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                val result = LinkedHashSet<User>()
                if (data != null) {
                    val users = data.value as Map<*, *>?
                    for ((key) in users!!){
                        val user = get(key as String).get()
                        if(user != null){
                            result.add(user)
                        }
                    }
                }
                return@Callable result
            }
            return@Callable null
        })
    }

    override fun update(key: String, user: User) {
        val userRef = usersRef.child(key)
        userRef.setValue(user)
    }

    override fun delete(key: String) {
        val userRef = usersRef.child(key)
        userRef.removeValue()
    }

    override fun deleteBar(barKey: String) {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {
            val task = usersRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                if (data != null) {
                    val users = data.value as Map<*, *>?
                    for ((key) in users!!) {
                        usersRef.child(key as String).child("bars").child(barKey).removeValue()
                    }
                }
            }
        }
    }

    /**
     * Class fields
     */
    companion object {
        /**
         * Reference to the [Firebase database][Firebase.database]
         */
        private val database = Firebase.database

        /**
         * Reference to the "users" path in the [database]
         */
        private val usersRef = database.getReference("users")
    }
}