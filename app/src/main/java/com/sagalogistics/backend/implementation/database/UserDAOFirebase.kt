package com.sagalogistics.backend.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.backend.api.database.UserDAO
import com.sagalogistics.backend.api.models.User
import com.sagalogistics.backend.implementation.models.UserImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class UserDAOFirebase : UserDAO { //temporary, will be changed after GoogleAuth integration
    companion object {
        private val database = Firebase.database
        private val usersRef = database.getReference("users")
    }

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
            val data = task.result
            val user: User? = data!!.getValue(UserImpl::class.java)
            if(user != null){
                user.key = data.key
                user
            }
            else{
                null
            }
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
                    val users = data.value as List<*>?
                    for (key in users!!) {
                        usersRef.child(key as String).child("bars").child(barKey).removeValue()
                    }
                }
            }
        }
    }
}