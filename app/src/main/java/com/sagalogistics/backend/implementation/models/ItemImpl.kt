package com.sagalogistics.backend.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator
import com.sagalogistics.backend.api.models.Item

class ItemImpl : Item, Parcelable {
    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override lateinit var name: String
    override var weight: Float = 0.0f
    override var image: String? = null

    /**
     * Empty constructor needed for serialization; should not be used
     */
    constructor() {}

    /**
     * @constructor standard constructor
     */
    constructor(key: String? = null, name: String, weight: Float, image: String? = null) {
        this.key = key
        this.name = name
        this.weight = weight
        this.image = image
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        weight = parcel.readFloat()
        image = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeFloat(weight)
        dest.writeString(image)
    }

    companion object CREATOR : Creator<ItemImpl> {
        override fun createFromParcel(parcel: Parcel): ItemImpl {
            return ItemImpl(parcel)
        }

        override fun newArray(size: Int): Array<ItemImpl?> {
            return arrayOfNulls(size)
        }
    }
}