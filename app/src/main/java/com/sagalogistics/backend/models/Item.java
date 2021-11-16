package com.sagalogistics.backend.models;

import android.os.Parcelable;

public interface Item extends Parcelable {
    String getKey();

    void setKey(String key);

    String getName();

    void setName(String name);

    Float getWeight();

    void setWeight(Float weight);
}
