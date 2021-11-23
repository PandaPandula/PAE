package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;

import com.sagalogistics.backend.models.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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

    @NonNull
    @Override
    public Item getItem(@NotNull String key) throws Exception {
        return itemDAO.get(key).get();
    }

    @Override
    public void updateItem(@NotNull String key, @NotNull Item item) {
        itemDAO.update(key, item);
    }

    @Override
    public void deleteItem(@NotNull String key) {
        itemDAO.delete(key);
    }
}
