package com.sagalogistics.backend.api.database

import com.sagalogistics.backend.api.models.Bar
import com.sagalogistics.backend.api.models.Item
import com.sagalogistics.backend.api.models.Order
import com.sagalogistics.backend.api.models.User
import java.lang.NullPointerException
import java.util.concurrent.Future

/**
 * Singleton class in charge of managing communication with the database
 *
 * Before using the other methods you must call [Repository.initialize];
 * trying to access the instance before will throw a [NullPointerException]
 *
 * @author Gerard Queralt
 * @constructor the [factory] responsible for creating each DAO
 */
class Repository private constructor(factory: RepositoryFactory) {
    /**
     * The DAO of the [Item] class
     */
    private val itemDAO: ItemDAO = factory.createItemDAO()

    /**
     * The DAO of the [Order] class
     */
    private val orderDAO: OrderDAO = factory.createOrderDAO()

    /**
     * The DAO of the [Bar] class
     */
    private val barDAO: BarDAO = factory.createBarDAO()

    /**
     * The DAO of the [User] class
     */
    private val userDAO: UserDAO = factory.createUserDAO()

    fun addItem(item: Item) {
        itemDAO.add(item)
    }

    fun getItem(key: String): Future<Item?> {
        return itemDAO.get(key)
    }

    fun getAllItems(): Future<Set<Item>?> {
        return itemDAO.getAll()
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

    fun getAllOrders(): Future<Set<Order>?> {
        return orderDAO.getAll()
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

    fun getAllBars(): Future<Set<Bar>?> {
        return barDAO.getAll()
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

    fun getAllUsers(): Future<Set<User>?> {
        return userDAO.getAll()
    }

    fun updateUser(key: String, user: User) {
        userDAO.update(key, user)
    }

    fun deleteUser(key: String) {
        userDAO.delete(key)
    }

    /**
     * Class properties
     */
    companion object {
        /**
         * Singleton instance
         */
        private var instance: Repository? = null

        /**
         * Initialize the [instance] using the [factory]
         *
         * This method must be called once before trying to [access][Repository.getInstance] the [instance]
         *
         * Calling this method additional times does nothing
         */
        fun initialize(factory: RepositoryFactory) {
            if (instance == null) instance = Repository(factory)
        }

        /**
         * Returns the singleton [instance] of the [Repository]
         *
         * This method can only be called after the [instance] has been [initialized][Repository.initialize]
         *
         * @throws [NullPointerException] if the [instance] is not [initialized][Repository.initialize]
         */
        fun getInstance(): Repository {
            if (instance == null) throw NullPointerException("Error: Repository not initialized")
            return instance!!
        }
    }
}