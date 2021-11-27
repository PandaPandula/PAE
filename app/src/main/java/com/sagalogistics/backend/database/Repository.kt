package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.Item
import com.sagalogistics.backend.models.Order
import java.lang.NullPointerException

class Repository private constructor(factory: RepositoryFactory) {
    private val itemDAO: ItemDAO = factory.createItemDAO()
    private val orderDAO: OrderDAO = factory.createOrderDAO()

    companion object {
        private var instance: Repository? = null

        fun initialize(factory: RepositoryFactory) {
            if (instance == null) instance = Repository(factory)
        }

        fun getInstance(): Repository {
            if (instance == null) throw NullPointerException("Error: Repository not initialized")
            return instance!!
        }
    }

    fun addItem(item: Item) {
        itemDAO.add(item)
    }

    fun getItem(key: String): Item? {
        return itemDAO.get(key).get()
    }

    fun updateItem(key: String, item: Item) {
        itemDAO.update(key, item)
    }

    fun deleteItem(key: String) {
        itemDAO.delete(key)
        orderDAO.deleteItem(key)
    }

    fun addOrder(order: Order) {
        orderDAO.add(order)
    }

    fun getOrder(key: String): Order? {
        return orderDAO.get(key).get()
    }

    fun updateOrder(key: String, order: Order) {
        orderDAO.update(key, order)
    }

    fun deleteOrder(key: String) {
        orderDAO.delete(key)
    }
}