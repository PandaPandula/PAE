package com.sagalogistics.backend.models;

import java.io.Serializable;
import java.util.Map;

public interface Order extends Serializable {
    Map<String, Integer> getOrder();

    void addItem(String key);

    Integer getQuantityItem(String key);
}