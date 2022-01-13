package com.sagalogistics.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import com.sagalogistics.lib.models.Order

/**
 * Implementation of the [Order] interface
 */
class OrderImpl : Order, Parcelable {
    /**
     * The key of the order in the database
     */
    @get:Exclude
    @set:Exclude
    override var key: String? = null

    /**
     * A binary to differentiate return orders from delivery orders
     */
    override var isReturn: Boolean = true

    /**
     * A [MutableMap] where keys are
     * [the keys of items in the database][com.sagalogistics.lib.models.Item.key]
     * and the values are its quantity in the order
     */
    override var items: MutableMap<String, Int>
        get() = LinkedHashMap(_items)
        set(value) {
            _items = LinkedHashMap(value)
        }

    /**
     * A backing property holding the actual [MutableMap]
     * of [items][com.sagalogistics.lib.models.Item] to make it true private;
     * that is, it can only be modified through the methods of [Order]
     */
    private var _items: MutableMap<String, Int> = LinkedHashMap()

    /**
     * Empty constructor needed for serialization
     */
    constructor() {}

    /**
     * Standard constructor
     */
    constructor(key: String? = null,
                items: MutableMap<String, Int> = LinkedHashMap(),
                isReturn: Boolean = true) {
        this.key = key
        this.items = items
        this.isReturn = isReturn
    }

    /**
     * Updates the quantity of an item in the order
     *
     * @throws IllegalArgumentException if the quantity is negative
     */
    override fun updateItem(itemKey: String, quantity: Int) {
        if (quantity < 0)
            throw IllegalArgumentException("Quantity of item can't be negative")
        _items[itemKey] = quantity
    }

    /**
     * Returns the quantity of a given item in the order
     */
    override fun getQuantityItem(itemKey: String): Int? {
        return _items[itemKey]
    }

    /**
     * Removes an item from the order
     */
    override fun removeItem(itemKey: String) {
        _items.remove(itemKey)
    }

    /**
     * Parcel constructor used for [Parcelable] implementation
     */
    constructor(parcel: Parcel) {
        key = parcel.readString()
        parcel.readMap(items, MutableMap::class.java.classLoader)
    }

    /**
     * Implementation of [Parcelable.writeToParcel]
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeMap(items)
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
    companion object CREATOR : Parcelable.Creator<OrderImpl> {
        /**
         * Creates an [OrderImpl] from a [parcel]
         */
        override fun createFromParcel(parcel: Parcel): OrderImpl {
            return OrderImpl(parcel)
        }

        /**
         * Returns an empty array of [OrderImpl] of a given [size]
         */
        override fun newArray(size: Int): Array<OrderImpl?> {
            return arrayOfNulls(size)
        }
    }
}