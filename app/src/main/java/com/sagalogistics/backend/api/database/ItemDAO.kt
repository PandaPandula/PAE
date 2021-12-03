package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Item
import java.util.concurrent.Future

interface ItemDAO {
    fun add(item: Item)
    fun get(key: String): Future<Item?>
    fun getAll(): Future<Set<Item>?>
    fun update(key: String, item: Item)
    fun delete(key: String)
}