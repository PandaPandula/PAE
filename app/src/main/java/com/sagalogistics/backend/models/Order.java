package com.sagalogistics.backend.models;

import java.io.Serializable;
import java.util.Map;

public interface Order extends Serializable {
    void addItem(String key, Integer quantity);

    Map<String, Integer> getOrder();

    Integer getQuantityItem(String key);

    void updateQuantity(String key, Integer newQuantity);

    void removeItem(String key);
}