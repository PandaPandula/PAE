package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Order
import java.util.concurrent.Future

/**
 * The data accessor object of the [Order] class
 */
interface OrderDAO {
    /**
     * Adds an [order] to the database
     */
    fun add(order: Order)

    /**
     * Gets an [order][Order] from the database identified by its [key],
     * or null if the [order][Order] cannot be retrieved
     */
    fun get(key: String): Future<Order?>

    /**
     * Gets all the [orders][Order] from the database
     */
    fun getAll(): Future<Set<Order>?>

    /**
     * Updates an [order] in the database
     */
    fun update(key: String, order: Order)

    /**
     * Deletes an [order][Order] from the database
     */
    fun delete(key: String)

    /**
     * Deletes an [item][com.sagalogistics.backend.api.models.Item] from all the [orders][Order]
     */
    fun deleteItem(itemKey: String)
}