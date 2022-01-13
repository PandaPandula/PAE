package com.sagalogistics.implementation.database

import com.sagalogistics.lib.database.*

/**
 * Implementation of [RepositoryFactory] using a Firebase database and the data accessor objects
 * in this package
 *
 * @constructor default constructor
 */
class RepositoryFactoryFirebase : RepositoryFactory {
    /**
     * Returns an instance of an [ItemDAOFirebase]
     */
    override fun createItemDAO(): ItemDAO {
        return ItemDAOFirebase()
    }

    /**
     * Returns an instance of an [OrderDAOFirebase]
     */
    override fun createOrderDAO(): OrderDAO {
        return OrderDAOFirebase()
    }

    /**
     * Returns an instance of a [BarDAOFirebase]
     */
    override fun createBarDAO(): BarDAO {
        return BarDAOFirebase()
    }

    /**
     * Returns an instance of a [UserDAOFirebase]
     */
    override fun createUserDAO(): UserDAO {
        return UserDAOFirebase()
    }
}