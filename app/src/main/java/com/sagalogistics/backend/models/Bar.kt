package com.sagalogistics.backend.models

interface Bar {
    var key: String?
    var name: String
    var orders: MutableList<String>

    fun addOrder(orderKey: String)
    fun removeOrder(orderKey: String)
}