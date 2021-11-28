package com.sagalogistics.backend.database

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
    fun createOrderDAO(): OrderDAO
    fun createBarDAO(): BarDAO
    fun createUserDAO(): UserDAO
}