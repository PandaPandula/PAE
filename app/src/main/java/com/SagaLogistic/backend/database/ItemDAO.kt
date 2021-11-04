package com.SagaLogistic.backend.database

import com.SagaLogistic.backend.models.Item
import java.util.concurrent.Future

interface ItemDAO {
    fun add(item: Item?)
    fun get(id: String?): Future<Item?>?
    fun update(id: String?, item: Item?)
    fun delete(id: String?)
}