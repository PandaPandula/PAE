package com.sagalogistics.backend.models;

import java.io.Serializable;

public interface Item extends Serializable {
    String getKey();

    void setKey(String key);

    String getName();

    void setName(String name);

    Float getWeight();

    void setWeight(Float weight);
}
