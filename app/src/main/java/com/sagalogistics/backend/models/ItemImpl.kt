package com.sagalogistics.backend.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator

class ItemImpl : Item, Parcelable {
    companion object CREATOR : Creator<ItemImpl> {
        override fun createFromParcel(parcel: Parcel): ItemImpl {
            return ItemImpl(parcel)
        }

        override fun newArray(size: Int): Array<ItemImpl?> {
            return arrayOfNulls(size)
        }
    }

    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override lateinit var name: String
    override var weight: Float = 0.0f

    /**
     * Empty constructor; needed for Firebase to serialize the result of a query
     */
    constructor() {}

    constructor(key: String?, name: String, weight: Float) {
        this.key = key
        this.name = name
        this.weight = weight
    }

    constructor(name: String, weight: Float) {
        this.name = name
        this.weight = weight
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        weight = parcel.readFloat()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeFloat(weight)
    }
}