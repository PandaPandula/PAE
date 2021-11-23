package com.sagalogistics.backend.database

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
    fun createOrderDAO(): OrderDAO
}