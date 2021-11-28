package com.sagalogistics.backend.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.sagalogistics.backend.models.Bar
import com.sagalogistics.backend.models.BarImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class BarDAOFirebase : BarDAO {
    companion object {
        private val database = FirebaseDatabase.getInstance()
        private val barsRef = database.getReference("bars")
    }

    override fun add(bar: Bar) {
        val key = bar.key
        if (key == null) {
            val pushedPostRef = barsRef.push()
            bar.key = pushedPostRef.key
            pushedPostRef.setValue(bar)
        } else {
            update(key, bar) //reuse code
        }
    }

    override fun get(key: String): Future<Bar?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = barsRef.child(key).get()
            Tasks.await(task)
            val data = task.result
            val bar: Bar? = data!!.getValue(BarImpl::class.java)
            if(bar != null){
                bar.key = data.key
                bar
            }
            else{
                null
            }
        })
    }

    override fun update(key: String, bar: Bar) {
        val barRef = barsRef.child(key)
        barRef.setValue(bar)
    }

    override fun delete(key: String) {
        val barRef = barsRef.child(key)
        barRef.removeValue()
    }

    override fun deleteOrder(orderKey: String) {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {
            val task = barsRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                if (data != null) {
                    val bars = data.value as List<*>?
                    for (key in bars!!) {
                        barsRef.child(key as String).child("orders").child(orderKey).removeValue()
                    }
                }
            }
        }
    }
}