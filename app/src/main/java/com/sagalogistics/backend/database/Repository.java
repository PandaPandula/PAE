package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;

import com.sagalogistics.backend.models.Item;
import com.sagalogistics.backend.models.Order;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Repository {
    private static Repository instance;

    private final ItemDAO itemDAO;
    private final OrderDAO orderDAO;

    public static void initialize(RepositoryFactory factory){
        if(instance == null)
            instance = new Repository(factory);
    }

    public static Repository getInstance(){
        return instance;
    }

    private Repository(RepositoryFactory factory){
        this.itemDAO = factory.createItemDAO();
        this.orderDAO = factory.createOrderDAO();
    }

    public void addItem(@NonNull Item item) {
        itemDAO.add(item);
    }

    @NonNull
    public Item getItem(@NotNull String key) throws Exception {
        return itemDAO.get(key).get();
    }

    public void updateItem(@NotNull String key, @NotNull Item item) {
        itemDAO.update(key, item);
    }

    public void deleteItem(@NotNull String key) {
        itemDAO.delete(key);
        orderDAO.deleteItem(key);
    }

    public void addOrder(@NonNull Order order) {
        orderDAO.add(order);
    }

    @NonNull
    public Order getOrder(@NonNull String key) throws Exception {
        return orderDAO.get(key).get();
    }

    public void updateOrder(@NonNull String key, @NonNull Order order) {
        orderDAO.update(key, order);
    }

    public void deleteOrder(@NonNull String key) {
        orderDAO.delete(key);
    }
}
