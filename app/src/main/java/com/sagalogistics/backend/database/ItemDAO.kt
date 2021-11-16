package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Item
import java.util.concurrent.Future

interface ItemDAO {
    fun add(item: Item)
    fun get(id: String): Future<Item>?
    fun update(id: String, item: Item)
    fun delete(id: String)
}