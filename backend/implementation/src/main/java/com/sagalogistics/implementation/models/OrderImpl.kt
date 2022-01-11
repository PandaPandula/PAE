package com.sagalogistics.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator
import com.sagalogistics.lib.models.Order

class OrderImpl : Order, Parcelable {
    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override var isReturn: Boolean = true
    override var items: MutableMap<String, Int>
        get() = LinkedHashMap(_items)
        set(value) {
            _items = LinkedHashMap(value)
        }
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

    override fun updateItem(itemKey: String, quantity: Int) {
        if (quantity < 0)
            throw IllegalArgumentException("Quantity of item can't be negative")
        _items[itemKey] = quantity
    }

    override fun getQuantityItem(itemKey: String): Int? {
        return _items[itemKey]
    }

    override fun removeItem(itemKey: String) {
        _items.remove(itemKey)
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        parcel.readMap(items, MutableMap::class.java.classLoader)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeMap(items)
    }

    companion object CREATOR : Creator<OrderImpl> {
        override fun createFromParcel(parcel: Parcel): OrderImpl {
            return OrderImpl(parcel)
        }

        override fun newArray(size: Int): Array<OrderImpl?> {
            return arrayOfNulls(size)
        }
    }
}