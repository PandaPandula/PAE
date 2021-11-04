package com.SagaLogistic.backend.database

import com.SagaLogistic.backend.models.Item
import java.util.concurrent.Future

interface Repository {
    fun addItem(item: Item)
    fun getItem(id: String): Future<Item>?
    fun updateItem(id: String, item: Item)
    fun deleteItem(id: String)
}