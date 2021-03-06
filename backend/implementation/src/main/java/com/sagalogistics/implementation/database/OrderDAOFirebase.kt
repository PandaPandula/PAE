package com.sagalogistics.implementation.database

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sagalogistics.lib.database.OrderDAO
import com.sagalogistics.lib.models.Order
import com.sagalogistics.implementation.models.OrderImpl
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Implementation of [OrderDAO] using a Firebase database and the [OrderImpl] class
 *
 * @constructor default constructor
 */
class OrderDAOFirebase : OrderDAO {
    /**
     * Adds an [order] to the database
     *
     * If it didn't have a [key][Order.key] it sets the key generated by Firebase;
     * if it had one it calls [OrderDAOFirebase.update]
     */
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

    /**
     * Gets an [order][Order] from the database identified by its [key],
     * or null if the [order][Order] cannot be retrieved
     *
     * Returns an instance of [OrderImpl]
     */
    override fun get(key: String): Future<Order?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = ordersRef.child(key).get()
            Tasks.await(task)
            if(task.isSuccessful) {
                val data = task.result
                val order: Order? = data!!.getValue(OrderImpl::class.java)
                if (order != null) {
                    order.key = data.key
                    return@Callable order
                }
            }
            return@Callable null
        })
    }

    /**
     * Gets all the [orders][Order] from the database
     */
    override fun getAll(): Future<Set<Order>?> {
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable {
            val task = ordersRef.get()
            Tasks.await(task)
            if (task.isSuccessful) {
                val data = task.result
                val result = LinkedHashSet<Order>()
                if (data != null) {
                    val orders = data.value as Map<*, *>?
                    for ((key) in orders!!){
                        val order = get(key as String).get()
                        if(order != null){
                            result.add(order)
                        }
                    }
                }
                return@Callable result
            }
            return@Callable null
        })
    }

    /**
     * Updates an [order] in the database
     */
    override fun update(key: String, order: Order) {
        val orderRef = ordersRef.child(key)
        orderRef.setValue(order)
    }

    /**
     * Deletes an [order][Order] from the database
     */
    override fun delete(key: String) {
        val orderRef = ordersRef.child(key)
        orderRef.removeValue()
    }

    /**
     * Deletes an [item][com.sagalogistics.lib.models.Item] from all the [orders][Order]
     */
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

    /**
     * Class fields
     */
    companion object {
        /**
         * Reference to the [Firebase database][Firebase.database]
         */
        private val database = Firebase.database

        /**
         * Reference to the "orders" path in the [database]
         */
        private val ordersRef = database.getReference("orders")
    }
}