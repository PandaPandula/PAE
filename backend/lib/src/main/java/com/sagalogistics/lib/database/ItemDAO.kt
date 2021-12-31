package com.sagalogistics.lib.database

import com.sagalogistics.lib.models.Item
import java.util.concurrent.Future

/**
 * The data accessor object of the [Item] class
 */
interface ItemDAO {
    /**
     * Adds an [item] to the database
     */
    fun add(item: Item)

    /**
     * Gets an [item][Item] from the database identified by its [key],
     * or null if the [item][Item] cannot be retrieved
     */
    fun get(key: String): Future<Item?>

    /**
     * Gets all the [items][Item] from the database
     */
    fun getAll(): Future<Set<Item>?>

    /**
     * Updates an [item] in the database
     */
    fun update(key: String, item: Item)

    /**
     * Deletes an [item][Item] from the database
     */
    fun delete(key: String)
}