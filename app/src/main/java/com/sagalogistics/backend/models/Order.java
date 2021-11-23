package com.sagalogistics.backend.models;

import java.io.Serializable;
import java.util.Map;

public interface Order extends Serializable {
    String getKey();

    void setKey(String key);

    Map<String, Integer> getItems();

    void setItems(Map<String, Integer> items);

    void updateItem(String key, Integer quantity);

    Integer getQuantityItem(String key);

    void removeItem(String key);
}