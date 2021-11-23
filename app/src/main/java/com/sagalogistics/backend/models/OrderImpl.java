package com.sagalogistics.backend.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class OrderImpl implements Order, Parcelable{

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public OrderImpl createFromParcel(Parcel in) {
            return new OrderImpl(in);
        }

        @Override
        public OrderImpl[] newArray(int size) {
            return new OrderImpl[size];
        }
    };

    private String key;
    private Map<String, Integer> items = new HashMap<>();

    public OrderImpl(){
    }

    public OrderImpl(String key, Map<String, Integer> items) {
        this.key = key;
        this.items = items;
    }

    public OrderImpl(Map<String, Integer> items){
        this.items = items;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items); //Creem una shallow copy per impedir modificacions a la ordre real
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public void updateItem(String key, Integer quantity) {
        items.put(key, quantity);
    }

    public Integer getQuantityItem(String key) {
        return items.get(key);
    }

    public void removeItem(String key) {
        items.remove(key);
    }

    protected OrderImpl(Parcel in) {
        this.key = in.readString();
        in.readMap(this.items, Map.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeMap(this.items);
    }
}