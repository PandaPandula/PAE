package com.sagalogistics.backend.database;

import com.sagalogistics.backend.models.Order;

import java.util.concurrent.Future;

public interface OrderDAO {
    void add(Order order);
    Future<Order> get(String key);
    void update(String key, Order order);
    void delete(String key);
    void deleteItem(String orderKey, String itemKey);
}
