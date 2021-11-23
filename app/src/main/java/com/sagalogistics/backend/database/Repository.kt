package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Item
import com.sagalogistics.backend.models.Order
import java.lang.Exception
import java.util.concurrent.Future
import kotlin.jvm.Throws

interface Repository {
    fun addItem(item: Item)
    @Throws (Exception::class) //quan migrem RepositoryImpl a Kotlin no caldra
    fun getItem(key: String): Item
    fun updateItem(key: String, item: Item)
    fun deleteItem(key: String)

    fun addOrder(order: Order)
    @Throws (Exception::class) //quan migrem RepositoryImpl a Kotlin no caldra
    fun getOrder(key: String): Order
    fun updateOrder(key: String, order: Order)
    fun deleteOrder(key: String)
}