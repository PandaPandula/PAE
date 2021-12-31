package com.sagalogistics.backend.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator
import com.sagalogistics.backend.api.models.Item
import java.lang.IllegalArgumentException

class ItemImpl : Item, Parcelable {
    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override lateinit var name: String
    override var weight: Float = 0f
        set(value) {
            if(value < 0)
                throw IllegalArgumentException("Weight can't be negative")
            field = value
        }
    override var upperVariance: Float = 0f
        set(value) {
            if(value < 0 || value > 100)
                throw IllegalArgumentException("Percentage must be between 0 and 100")
            field = value
        }
    override var lowerVariance: Float = 0f
        set(value) {
            if(value < 0 || value > 100)
                throw IllegalArgumentException("Percentage must be between 0 and 100")
            field = value
        }
    override var image: String? = null

    /**
     * Empty constructor needed for serialization; should not be used
     */
    constructor() {}

    /**
     * Standard constructor
     */
    constructor(key: String? = null, name: String, weight: Float, upperVariance: Float = 0f, lowerVariance: Float = upperVariance, image: String? = null) {
        this.key = key
        this.name = name
        this.weight = weight
        this.upperVariance = upperVariance
        this.lowerVariance = lowerVariance
        this.image = image
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        weight = parcel.readFloat()
        upperVariance = parcel.readFloat()
        lowerVariance = parcel.readFloat()
        image = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeFloat(weight)
        dest.writeFloat(upperVariance)
        dest.writeFloat(lowerVariance)
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