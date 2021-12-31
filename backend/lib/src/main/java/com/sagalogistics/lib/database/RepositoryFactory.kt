package com.sagalogistics.lib.database

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
    fun createOrderDAO(): OrderDAO
    fun createBarDAO(): BarDAO
    fun createUserDAO(): UserDAO
}