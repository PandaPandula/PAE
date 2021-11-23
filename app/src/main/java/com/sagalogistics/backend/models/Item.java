package com.sagalogistics.backend.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public interface Item extends Serializable, Cloneable {
    String getKey();

    void setKey(String key);

    String getName();

    void setName(String name);

    Float getWeight();

    void setWeight(Float weight);

    @NonNull
    Object clone() throws CloneNotSupportedException;
}