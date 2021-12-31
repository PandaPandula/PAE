package com.sagalogistics.implementation.database

import com.google.android.gms.tasks.Tasks
import com.sagalogistics.implementation.models.ItemImpl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.lib.database.ItemDAO
import com.sagalogistics.lib.models.Item
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ItemDAOFirebase : ItemDAO {
    override fun add(item: Item) {
        val key = item.key
        if (key == null) {
            val pushedPostRef = itemsRef.push()
            item.key = pushedPostRef.key
            pushedPostRef.setValue(item)
        } else {
            update(key, item) //reuse code
        }
    }

    override fun get(key: String): Future<Item?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = itemsRef.child(key).get()
            Tasks.await(task)
            if(task.isSuccessful){
                val data = task.result
                val item: Item? = data!!.getValue(ItemImpl::class.java)
                if (item != null) {
                    item.key = data.key
                    return@Callable item
                }
            }
            return@Callable null
        })
    }

    override fun getAll(): Future<Set<Item>?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = itemsRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                val result = LinkedHashSet<Item>()
                if (data != null) {
                    val items = data.value as Map<*, *>?
                    for ((key) in items!!){
                        val item = get(key as String).get()
                        if(item != null){
                            result.add(item)
                        }
                    }
                }
                return@Callable result
            }
            return@Callable null
        })
    }

    override fun update(key: String, item: Item) {
        val itemRef = itemsRef.child(key)
        itemRef.setValue(item)
    }

    override fun delete(key: String) {
        val itemRef = itemsRef.child(key)
        itemRef.removeValue()
    }

    companion object {
        private val database = Firebase.database
        private val itemsRef = database.getReference("items")
    }
}