package com.sagalogistics.lib.database

import com.sagalogistics.lib.models.User
import java.util.concurrent.Future

/**
 * The data accessor object of the [User] class
 */
interface UserDAO {
    /**
     * Adds a [user] to the database
     */
    fun add(user: User)

    /**
     * Gets a [user][User] from the database identified by its [key],
     * or null if the [user][User] cannot be retrieved
     */
    fun get(key: String): Future<User?>

    /**
     * Gets all the [users][User] from the database
     */
    fun getAll(): Future<Set<User>?>

    /**
     * Updates a [user] in the database
     */
    fun update(key: String, user: User)

    /**
     * Deletes a [user][User] from the database
     */
    fun delete(key: String)

    /**
     * Deletes a [bar][com.sagalogistics.lib.models.Bar] from all the [users][User]
     */
    fun deleteBar(barKey: String)
}