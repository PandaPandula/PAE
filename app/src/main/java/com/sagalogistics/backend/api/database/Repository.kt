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
 * @constructor private constructor to prevent external instantiation
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

    /**
     * Adds an [item] to the database
     */
    fun addItem(item: Item) {
        itemDAO.add(item)
    }

    /**
     * Gets the [item][Item] in the database identified by a given [key],
     * or null if it doesn't exist
     *
     * Returns a [Future] representing the result of the asynchronous "get"
     * (or equivalent) to the database
     */
    fun getItem(key: String): Future<Item?> {
        return itemDAO.get(key)
    }

    /**
     * Gets all the [items][Item] in the database
     *
     * Returns a [Future] representing a set of all the [items][Item]
     */
    fun getAllItems(): Future<Set<Item>?> {
        return itemDAO.getAll()
    }

    /**
     * Updates an [item][Item] in the database identified by a given [key]
     * and sets its fields to be equal to a given [item]
     */
    fun updateItem(key: String, item: Item) {
        itemDAO.update(key, item)
    }

    /**
     * Deletes an [item][Item] in the database identified by a given [key]
     *
     * It also deletes it from all the [orders][Order], in case the database is non-relational
     */
    fun deleteItem(key: String) {
        itemDAO.delete(key)
        orderDAO.deleteItem(key)
    }

    /**
     * Adds an [order] to the database
     */
    fun addOrder(order: Order) {
        orderDAO.add(order)
    }

    /**
     * Gets the [order][Order] in the database identified by a given [key],
     * or null if it doesn't exist
     *
     * Returns a [Future] representing the result of the asynchronous "get"
     * (or equivalent) to the database
     */
    fun getOrder(key: String): Future<Order?> {
        return orderDAO.get(key)
    }

    /**
     * Gets all the [orders][Order] in the database
     *
     * Returns a [Future] representing a set of all the [orders][Order]
     */
    fun getAllOrders(): Future<Set<Order>?> {
        return orderDAO.getAll()
    }

    /**
     * Updates an [order][Order] in the database identified by a given [key]
     * and sets its fields to be equal to a given [order]
     */
    fun updateOrder(key: String, order: Order) {
        orderDAO.update(key, order)
    }

    /**
     * Deletes an [order][Order] in the database identified by a given [key]
     *
     * It also deletes it from all the [bars][Bar], in case the database is non-relational
     */
    fun deleteOrder(key: String) {
        orderDAO.delete(key)
        barDAO.deleteOrder(key)
    }

    /**
     * Adds a [bar] to the database
     */
    fun addBar(bar: Bar){
        barDAO.add(bar)
    }

    /**
     * Gets the [bar][Bar] in the database identified by a given [key],
     * or null if it doesn't exist
     *
     * Returns a [Future] representing the result of the asynchronous "get"
     * (or equivalent) to the database
     */
    fun getBar(key: String): Future<Bar?> {
        return barDAO.get(key)
    }

    /**
     * Gets all the [bars][Bar] in the database
     *
     * Returns a [Future] representing a set of all the [bars][Bar]
     */
    fun getAllBars(): Future<Set<Bar>?> {
        return barDAO.getAll()
    }

    /**
     * Updates a [bar][Bar] in the database identified by a given [key]
     * and sets its fields to be equal to a given [bar]
     */
    fun updateBar(key: String, bar: Bar) {
        barDAO.update(key, bar)
    }

    /**
     * Deletes a [bar][Bar] in the database identified by a given [key]
     *
     * It also deletes it from all the [users][User], in case the database is non-relational
     */
    fun deleteBar(key: String) {
        barDAO.delete(key)
        userDAO.deleteBar(key)
    }

    /**
     * Adds a [user] to the database
     */
    fun addUser(user: User){
        userDAO.add(user)
    }

    /**
     * Gets the [user][User] in the database identified by a given [key],
     * or null if it doesn't exist
     *
     * Returns a [Future] representing the result of the asynchronous "get"
     * (or equivalent) to the database
     */
    fun getUser(key: String): Future<User?> {
        return userDAO.get(key)
    }

    /**
     * Gets all the [users][User] in the database
     *
     * Returns a [Future] representing a set of all the [users][User]
     */
    fun getAllUsers(): Future<Set<User>?> {
        return userDAO.getAll()
    }

    /**
     * Updates a [user][User] in the database identified by a given [key]
     * and sets its fields to be equal to a given [user]
     */
    fun updateUser(key: String, user: User) {
        userDAO.update(key, user)
    }

    /**
     * Deletes a [user][User] in the database identified by a given [key]
     */
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
         * Initialize the [instance] using the [factory] to create its DAOs
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