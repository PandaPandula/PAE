package com.sagalogistics.lib.models

import java.io.Serializable

interface Bar : Serializable{
    var key: String?
    var name: String
    var orders: MutableList<String>

    fun addOrder(orderKey: String)
    fun removeOrder(orderKey: String)
}