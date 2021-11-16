package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sagalogistics.backend.models.Item;

import java.util.concurrent.Future;

public class RepositoryImpl implements Repository{
    private final ItemDAO itemDAO;

    public RepositoryImpl(RepositoryFactory factory){
        this.itemDAO = factory.createItemDAO();
    }

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
