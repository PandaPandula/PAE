package com.sagalogistics.backend.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

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

    private String identifier;
    private Map<String, Integer> items;

    public OrderImpl(){
    }

    public OrderImpl(String identifier) {
        this.identifier = identifier;
        this.items = new HashMap<String, Integer>();
    }

    public OrderImpl(String identifier, Map<String, Integer> items) {
        this.identifier = identifier;
        this.items = items;
    }

    @Override
    public Map<String, Integer> getOrder() {
        return items;
    }

    @Override
    public void addItem(String key) {
        int aux;
        if(items.containsKey(key)) {
            aux = items.get(key);
            ++aux;
            items.remove(key);
            items.put(key, aux);
        }
        else
            items.put(key,1);
    }

    @Override
    public Integer getQuantityItem(String key) {
        return items.get(key);
    }

    protected OrderImpl(Parcel in) {
        this.identifier = in.readString();
        in.readMap(this.items, Map.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeMap(this.items);
    }
}