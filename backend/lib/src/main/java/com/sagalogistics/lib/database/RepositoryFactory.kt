package com.sagalogistics.lib.database

/**
 * Factory class used to create the data accessor objects for the [repository][Repository]
 *
 * This class is only intended to be used as a parameter of [Repository.initialize]
 */
interface RepositoryFactory {
    /**
     * Returns an instance of an [ItemDAO]
     */
    fun createItemDAO(): ItemDAO

    /**
     * Returns an instance of an [OrderDAO]
     */
    fun createOrderDAO(): OrderDAO

    /**
     * Returns an instance of a [BarDAO]
     */
    fun createBarDAO(): BarDAO

    /**
     * Returns an instance of a [UserDAO]
     */
    fun createUserDAO(): UserDAO
}