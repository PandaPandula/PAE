package com.sagalogistics.backend.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.sagalogistics.backend.api.database.OrderDAO
import com.sagalogistics.backend.api.models.Order
import com.sagalogistics.backend.implementation.models.OrderImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class OrderDAOFirebase : OrderDAO {
    companion object {
        private val database = FirebaseDatabase.getInstance()
        private val ordersRef = database.getReference("orders")
    }

    override fun add(order: Order) {
        val key = order.key
        if (key == null) {
            val pushedPostRef = ordersRef.push()
            order.key = pushedPostRef.key
            pushedPostRef.setValue(order)
        } else {
            update(key, order) //reuse code
        }
    }

    override fun get(key: String): Future<Order?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = ordersRef.child(key).get()
            Tasks.await(task)
            val data = task.result
            val order: Order? = data!!.getValue(OrderImpl::class.java)
            if(order != null){
                order.key = data.key
                order
            }
            else{
                null
            }
        })
    }

    override fun update(key: String, order: Order) {
        val orderRef = ordersRef.child(key)
        orderRef.setValue(order)
    }

    override fun delete(key: String) {
        val orderRef = ordersRef.child(key)
        orderRef.removeValue()
    }

    override fun deleteItem(itemKey: String) {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {
            val task = ordersRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                if (data != null) {
                    val orders = data.value as Map<*, *>?
                    for ((key) in orders!!) {
                        ordersRef.child(key as String).child("items").child(itemKey).removeValue()
                    }
                }
            }
        }
    }
}