package com.sagalogistics.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator
import com.sagalogistics.lib.models.Item

/**
 * Implementation of the [Item] interface
 */
class ItemImpl : Item, Parcelable {
    /**
     * The key of the item in the database
     */
    @get:Exclude
    @set:Exclude
    override var key: String? = null

    /**
     * The name of the item
     */
    override lateinit var name: String

    /**
     * The weight of the item
     *
     * @throws IllegalArgumentException if the weight is negative
     */
    override var weight: Float = 0f
        set(value) {
            if(value < 0)
                throw IllegalArgumentException("Weight can't be negative")
            field = value
        }

    /**
     * The upper variance percentage in weight of the item
     *
     * @throws IllegalArgumentException if the percentage is not between 0 and 100
     */
    override var upperVariance: Float = 0f
        set(value) {
            if(value < 0 || value > 100)
                throw IllegalArgumentException("Percentage must be between 0 and 100")
            field = value
        }

    /**
     * The lower variance percentage in weight of the item
     *
     * @throws IllegalArgumentException if the percentage is not between 0 and 100
     */
    override var lowerVariance: Float = 0f
        set(value) {
            if(value < 0 || value > 100)
                throw IllegalArgumentException("Percentage must be between 0 and 100")
            field = value
        }

    /**
     * The url of an image representing the item
     */
    override var image: String? = null

    /**
     * Empty constructor needed for serialization; should not be used
     */
    constructor() {}

    /**
     * Standard constructor
     */
    constructor(key: String? = null,
                name: String,
                weight: Float,
                upperVariance: Float = 0f,
                lowerVariance: Float = upperVariance,
                image: String? = null) {
        this.key = key
        this.name = name
        this.weight = weight
        this.upperVariance = upperVariance
        this.lowerVariance = lowerVariance
        this.image = image
    }

    /**
     * Parcel constructor used for [Parcelable] implementation
     */
    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        weight = parcel.readFloat()
        upperVariance = parcel.readFloat()
        lowerVariance = parcel.readFloat()
        image = parcel.readString()
    }

    /**
     * Implementation of [Parcelable.describeContents]
     *
     * Returns 0
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * Implementation of [Parcelable.writeToParcel]
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeFloat(weight)
        dest.writeFloat(upperVariance)
        dest.writeFloat(lowerVariance)
        dest.writeString(image)
    }

    /**
     * [Creator] for the [Parcelable] implementation
     */
    companion object CREATOR : Creator<ItemImpl> {
        /**
         * Creates an [ItemImpl] from a [parcel]
         */
        override fun createFromParcel(parcel: Parcel): ItemImpl {
            return ItemImpl(parcel)
        }

        /**
         * Returns an array of [size] nulls
         */
        override fun newArray(size: Int): Array<ItemImpl?> {
            return arrayOfNulls(size)
        }
    }
}