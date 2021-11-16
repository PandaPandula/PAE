package com.SagaLogistic.backend.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemImpl implements Item{

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public ItemImpl createFromParcel(Parcel in) {
            return new ItemImpl(in);
        }

        @Override
        public ItemImpl[] newArray(int size) {
            return new ItemImpl[size];
        }
    };

    private String key;
    private String name;
    private Float weight;

    public ItemImpl(String key, String name, Float weight){
        this.key = key;
        this.name = name;
        this.weight = weight;
    }

    public ItemImpl(String name, Float weight){
        this.name = name;
        this.weight = weight;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public ItemImpl(Parcel in){
        this.key = in.readString();
        this.name = in.readString();
        this.weight = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.name);
        dest.writeFloat(this.weight);
    }
}
