package com.sagalogistics.lib.database

import com.sagalogistics.lib.models.Bar
import java.util.concurrent.Future

/**
 * The data accessor object of the [Bar] class
 */
interface BarDAO {
    /**
     * Adds a [bar] to the database
     */
    fun add(bar: Bar)

    /**
     * Gets a [bar][Bar] from the database identified by its [key],
     * or null if the [bar][Bar] cannot be retrieved
     */
    fun get(key: String): Future<Bar?>

    /**
     * Gets all the [bars][Bar] from the database
     */
    fun getAll(): Future<Set<Bar>?>

    /**
     * Updates a [bar] in the database
     */
    fun update(key: String, bar: Bar)

    /**
     * Deletes a [bar][Bar] from the database
     */
    fun delete(key: String)

    /**
     * Deletes an [order][com.sagalogistics.api.models.Order] from all the [bars][Bar]
     */
    fun deleteOrder(orderKey: String)
}