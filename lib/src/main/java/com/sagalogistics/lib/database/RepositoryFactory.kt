package com.sagalogistics.lib.database

import com.sagalogistics.lib.database.UserDAO

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
    fun createOrderDAO(): OrderDAO
    fun createBarDAO(): BarDAO
    fun createUserDAO(): UserDAO
}