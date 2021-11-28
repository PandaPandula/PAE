package com.sagalogistics.backend.database

class RepositoryFactoryFirebase : RepositoryFactory {
    override fun createItemDAO(): ItemDAO {
        return ItemDAOFirebase()
    }

    override fun createOrderDAO(): OrderDAO {
        return OrderDAOFirebase()
    }

    override fun createBarDAO(): BarDAO {
        return BarDAOFirebase()
    }

    override fun createUserDAO(): UserDAO {
        return UserDAOFirebase()
    }
}