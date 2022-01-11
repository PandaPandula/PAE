package com.sagalogistics.lib.models

import java.io.Serializable

/**
 * An order
 */
interface Order : Serializable {
    /**
     * The key of the order in the database
     */
    var key: String?

    /**
     * A [MutableMap] where keys are [the keys of items in the database][Item.key]
     * and the values are its quantity in the order
     */
    var items: MutableMap<String, Int>

    /**
     * A binary to differentiate return orders from delivery orders
     */
    var isReturn: Boolean

    /**
     * Updates the quantity of an item in the order
     */
    fun updateItem(itemKey: String, quantity: Int)

    /**
     * Returns the quantity of a given item in the order
     */
    fun getQuantityItem(itemKey: String): Int?

    /**
     * Removes an item from the order
     */
    fun removeItem(itemKey: String)
}