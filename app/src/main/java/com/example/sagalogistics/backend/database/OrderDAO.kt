package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Order
import java.util.concurrent.Future

interface OrderDAO {
    fun add(order: Order)
    fun get(key: String): Future<Order?>
    fun update(key: String, order: Order)
    fun delete(key: String)
    fun deleteItem(itemKey: String)
}