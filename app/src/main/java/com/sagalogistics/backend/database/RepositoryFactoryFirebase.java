package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;

public class RepositoryFactoryFirebase implements RepositoryFactory{
    @NonNull
    @Override
    public ItemDAO createItemDAO() {
        return new ItemDAOFirebase();
    }

    @NonNull
    @Override
    public OrderDAO createOrderDAO() {
        return new OrderDAOFirebase();
    }
}
