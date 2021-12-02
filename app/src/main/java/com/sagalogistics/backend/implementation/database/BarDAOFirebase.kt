package com.sagalogistics.backend.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.backend.api.database.BarDAO
import com.sagalogistics.backend.api.models.Bar
import com.sagalogistics.backend.implementation.models.BarImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class BarDAOFirebase : BarDAO {
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

    companion object {
        private val database = Firebase.database
        private val barsRef = database.getReference("bars")
    }
}