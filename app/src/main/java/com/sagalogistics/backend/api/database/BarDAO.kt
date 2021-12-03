package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Bar
import java.util.concurrent.Future

interface BarDAO {
    fun add(bar: Bar)
    fun get(key: String): Future<Bar?>
    fun getAll(): Future<Set<Bar>?>
    fun update(key: String, bar: Bar)
    fun delete(key: String)
    fun deleteOrder(orderKey: String)
}