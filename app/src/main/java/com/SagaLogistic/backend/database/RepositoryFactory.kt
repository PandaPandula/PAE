package com.SagaLogistic.backend.database

interface RepositoryFactory {
    fun createItemDAO(): ItemDAO
}