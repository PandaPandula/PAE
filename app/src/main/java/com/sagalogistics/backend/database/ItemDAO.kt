package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Item
import java.util.concurrent.Future

interface ItemDAO {
    fun add(item: Item)
    fun get(key: String): Future<Item?>
    fun update(key: String, item: Item)
    fun delete(key: String)
}