package com.sagalogistics.backend.models;

public interface Item {
    String getKey();

    void setKey(String key);

    String getName();

    void setName(String name);

    Float getWeight();

    void setWeight(Float weight);
}
