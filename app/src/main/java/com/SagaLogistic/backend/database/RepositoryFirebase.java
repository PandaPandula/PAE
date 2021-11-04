package com.SagaLogistic.backend.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.SagaLogistic.backend.models.Item;

import java.util.concurrent.Future;

public class RepositoryFirebase implements Repository{
    private static final ItemDAOFirebase itemDAO = new ItemDAOFirebase();

    @Override
    public void addItem(@NonNull Item item) {
        itemDAO.add(item);
    }

    @Nullable
    @Override
    public Future<Item> getItem(@NonNull String id) {
        return itemDAO.get(id);
    }

    @Override
    public void updateItem(@NonNull String id, @NonNull Item item) {
        itemDAO.update(id, item);
    }

    @Override
    public void deleteItem(@NonNull String id) {
        itemDAO.delete(id);
    }
}
