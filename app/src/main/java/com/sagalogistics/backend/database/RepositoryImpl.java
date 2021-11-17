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

    private static final Map<String, Item> loadedItems = new HashMap<>();

    public RepositoryImpl(RepositoryFactory factory){
        this.itemDAO = factory.createItemDAO();
    }

    @Override
    public void addItem(@NonNull Item item) {
        itemDAO.add(item);
        loadedItems.put(item.getKey(), item);
    }

    @NonNull
    @Override
    public Item getItem(@NotNull String key) throws Exception {
        Item item = loadedItems.get(key);
        if(item == null){
            item = itemDAO.get(key).get();
        }
        return item;
    }

    @Override
    public void updateItem(@NotNull String key, @NotNull Item item) {
        itemDAO.update(key, item);
        loadedItems.put(key, item);
    }

    @Override
    public void deleteItem(@NotNull String key) {
        itemDAO.delete(key);
        loadedItems.remove(key);
    }
}
