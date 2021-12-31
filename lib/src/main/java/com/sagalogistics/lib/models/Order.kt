package com.sagalogistics.lib.models

import java.io.Serializable

interface Order : Serializable {
    var key: String?
    var items: MutableMap<String, Int>

    fun updateItem(itemKey: String, quantity: Int)
    fun getQuantityItem(itemKey: String): Int?
    fun removeItem(itemKey: String)
}