package com.sagalogistics.lib.models

import java.io.Serializable

/**
 * A user
 */
interface User : Serializable{
    /**
     * The key of the user in the database
     */
    var key: String?

    /**
     * The name of the user
     */
    var name: String

    /**
     * A [MutableList] with the [keys of the bars][Bar.key] assigned to the user
     */
    var bars: MutableList<String>

    /**
     * Assigns a bar to the user
     */
    fun addBar(barKey: String)

    /**
     * Removes a bar from the user's assignments
     */
    fun removeBar(barKey: String)
}