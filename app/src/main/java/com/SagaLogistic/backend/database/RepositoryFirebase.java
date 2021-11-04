package com.SagaLogistic.backend.database;

import androidx.annotation.Nullable;

import com.SagaLogistic.backend.models.Item;

import java.util.concurrent.Future;

public class RepositoryFirebase implements Repository{
    private static final ItemDAOFirebase itemDAO = new ItemDAOFirebase();

    @Override
    public void addItem(@Nullable Item item) {
        itemDAO.add(item);
    }

    @Nullable
    @Override
    public Future<Item> getItem(@Nullable String id) {
        return itemDAO.get(id);
    }

    @Override
    public void updateItem(@Nullable String id, @Nullable Item item) {
        itemDAO.update(id, item);
    }

    @Override
    public void deleteItem(@Nullable String id) {
        itemDAO.delete(id);
    }
}
