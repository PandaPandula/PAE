package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Item
import java.util.concurrent.Future

interface Repository {
    fun addItem(item: Item)
    fun getItem(key: String): Future<Item>
    fun updateItem(key: String, item: Item)
    fun deleteItem(key: String)
}