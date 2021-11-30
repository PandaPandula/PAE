package com.sagalogistics.backend.api.database

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
    fun createOrderDAO(): OrderDAO
    fun createBarDAO(): BarDAO
    fun createUserDAO(): UserDAO
}