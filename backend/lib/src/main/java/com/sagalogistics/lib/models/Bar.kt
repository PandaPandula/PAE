package com.sagalogistics.lib.models

import java.io.Serializable

/**
 * A bar
 */
interface Bar : Serializable{
    /**
     * The key of the bar in the database
     */
    var key: String?

    /**
     * The name of the bar
     */
    var name: String

    /**
     * A [MutableList] with the [keys of the orders][Order.key] of this bar
     */
    var orders: MutableList<String>

    /**
     * Adds an order to the bar
     */
    fun addOrder(orderKey: String)

    /**
     * Removes an order from the bar
     */
    fun removeOrder(orderKey: String)
}