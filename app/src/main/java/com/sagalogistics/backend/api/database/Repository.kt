package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Bar
import com.sagalogistics.backend.api.models.Item
import com.sagalogistics.backend.api.models.Order
import com.sagalogistics.backend.api.models.User
import java.lang.NullPointerException
import java.util.concurrent.Future

class Repository private constructor(factory: RepositoryFactory) {
    private val itemDAO: ItemDAO = factory.createItemDAO()
    private val orderDAO: OrderDAO = factory.createOrderDAO()
    private val barDAO: BarDAO = factory.createBarDAO()
    private val userDAO: UserDAO = factory.createUserDAO()

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

    fun getItem(key: String): Future<Item?> {
        return itemDAO.get(key)
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

    fun getOrder(key: String): Future<Order?> {
        return orderDAO.get(key)
    }

    fun updateOrder(key: String, order: Order) {
        orderDAO.update(key, order)
    }

    fun deleteOrder(key: String) {
        orderDAO.delete(key)
        barDAO.deleteOrder(key)
    }

    fun addBar(bar: Bar){
        barDAO.add(bar)
    }

    fun getBar(key: String): Future<Bar?> {
        return barDAO.get(key)
    }

    fun updateBar(key: String, bar: Bar) {
        barDAO.update(key, bar)
    }

    fun deleteBar(key: String) {
        barDAO.delete(key)
        userDAO.deleteBar(key)
    }

    fun addUser(user: User){
        userDAO.add(user)
    }

    fun getUser(key: String): Future<User?> {
        return userDAO.get(key)
    }

    fun updateUser(key: String, user: User) {
        userDAO.update(key, user)
    }

    fun deleteUser(key: String) {
        userDAO.delete(key)
    }
}