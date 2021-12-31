package com.sagalogistics.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.lib.database.BarDAO
import com.sagalogistics.lib.models.Bar
import com.sagalogistics.implementation.models.BarImpl
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
            if(task.isSuccessful) {
                val data = task.result
                val bar: Bar? = data!!.getValue(BarImpl::class.java)
                if (bar != null) {
                    bar.key = data.key
                    return@Callable bar
                }
            }
            return@Callable null
        })
    }

    override fun getAll(): Future<Set<Bar>?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = barsRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                val result = LinkedHashSet<Bar>()
                if (data != null) {
                    val bars = data.value as Map<*, *>?
                    for ((key) in bars!!){
                        val bar = get(key as String).get()
                        if(bar != null){
                            result.add(bar)
                        }
                    }
                }
                return@Callable result
            }
            return@Callable null
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
                    val bars = data.value as Map<*, *>?
                    for ((key) in bars!!) {
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