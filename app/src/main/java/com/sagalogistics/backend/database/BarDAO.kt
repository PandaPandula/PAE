package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Bar
import java.util.concurrent.Future

interface BarDAO {
    fun add(bar: Bar)
    fun get(key: String): Future<Bar?>
    fun update(key: String, bar: Bar)
    fun delete(key: String)
    fun deleteOrder(orderKey: String)
}