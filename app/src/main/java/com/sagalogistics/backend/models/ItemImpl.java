package com.sagalogistics.backend.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class ItemImpl implements Item, Parcelable{

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

    /**
     * Empty constructor; needed for Firebase to serialize the result of a query
     */
    public ItemImpl(){}

    public ItemImpl(String key, String name, Float weight){
        this.key = key;
        this.name = name;
        this.weight = weight;
    }

    public ItemImpl(String name, Float weight){
        this.name = name;
        this.weight = weight;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
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

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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