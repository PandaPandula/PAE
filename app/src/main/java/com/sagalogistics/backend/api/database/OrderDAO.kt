package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Order
import java.util.concurrent.Future

interface OrderDAO {
    fun add(order: Order)
    fun get(key: String): Future<Order?>
    fun update(key: String, order: Order)
    fun delete(key: String)
    fun deleteItem(itemKey: String)
}