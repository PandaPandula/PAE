package com.sagalogistics.backend.database

import com.google.android.gms.tasks.Tasks
import com.sagalogistics.backend.models.ItemImpl
import com.google.firebase.database.FirebaseDatabase
import com.sagalogistics.backend.models.Item
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ItemDAOFirebase : ItemDAO {
    companion object {
        private val database = FirebaseDatabase.getInstance()
        private val itemsRef = database.getReference("items")
    }

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

    override operator fun get(key: String): Future<Item?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = itemsRef.child(key).get()
            Tasks.await(task)
            if(task.isSuccessful){
                val data = task.result
                val item: Item? = data!!.getValue(ItemImpl::class.java)
                item!!.key = data.key
                item
            }
            else{
                null
            }
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
}