package com.sagalogistics.backend.models

import java.io.Serializable

interface Order : Serializable {
    var key: String?
    var items: MutableMap<String, Int>
    fun updateItem(key: String, quantity: Int)
    fun getQuantityItem(key: String): Int?
    fun removeItem(key: String)
}