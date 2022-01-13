package com.sagalogistics.implementation.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.sagalogistics.lib.models.Bar

/**
 * Implementation of the [Bar] interface
 */
class BarImpl : Bar, Parcelable{
    /**
     * The key of the bar in the database
     */
    @get:Exclude
    @set:Exclude
    override var key: String? = null

    /**
     * The name of the bar
     */
    override lateinit var name: String

    /**
     * A [MutableList] with the
     * [keys of the orders][com.sagalogistics.lib.models.Order.key] of this bar
     */
    override var orders: MutableList<String>
        get() = ArrayList(_orders)
        set(value) {
            _orders = ArrayList(value)
        }

    /**
     * A backing property holding the actual [MutableList]
     * of [orders][com.sagalogistics.lib.models.Order] to make it true private;
     * that is, it can only be modified through the methods of [Bar]
     */
    private var _orders: MutableList<String> = ArrayList()

    /**
     * The address of the bar
     *
     * For front-end purposes mainly
     */
    override var address: String? = null

    /**
     * The url of an image of the bar
     */
    override var image: String? = null

    /**
     * Empty constructor needed for serialization
     *
     * Should not be used, since a bar is expected to have a [name]
     */
    constructor() {}

    /**
     * Standard constructor
     */
    constructor(key: String? = null,
                name: String,
                orders: MutableList<String> = ArrayList(),
                address: String? = null,
                image: String? = null){
        this.key = key
        this.name = name
        this.orders = orders
        this.address = address
        this.image = image
    }

    /**
     * Adds an order to the bar
     */
    override fun addOrder(orderKey: String) {
        _orders.add(orderKey)
    }

    /**
     * Removes an order from the bar
     */
    override fun removeOrder(orderKey: String) {
        _orders.remove(orderKey)
    }

    /**
     * Parcel constructor used for [Parcelable] implementation
     */
    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        parcel.readList(orders, MutableList::class.java.classLoader)
    }

    /**
     * Implementation of [Parcelable.writeToParcel]
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeList(orders)
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
     * [Creator][Parcelable.Creator] for the [Parcelable] implementation
     */
    companion object CREATOR : Parcelable.Creator<BarImpl> {
        /**
         * Creates a [BarImpl] from a [parcel]
         */
        override fun createFromParcel(parcel: Parcel): BarImpl {
            return BarImpl(parcel)
        }

        /**
         * Returns an empty array of [BarImpl] of a given [size]
         */
        override fun newArray(size: Int): Array<BarImpl?> {
            return arrayOfNulls(size)
        }
    }
}