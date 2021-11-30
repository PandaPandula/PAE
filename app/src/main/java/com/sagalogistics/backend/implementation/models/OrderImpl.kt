package com.sagalogistics.backend.implementation.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator
import com.sagalogistics.backend.api.models.Order

class OrderImpl : Order, Parcelable {
    companion object CREATOR : Creator<OrderImpl> {
        override fun createFromParcel(parcel: Parcel): OrderImpl {
            return OrderImpl(parcel)
        }

        override fun newArray(size: Int): Array<OrderImpl?> {
            return arrayOfNulls(size)
        }
    }

    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override var items: MutableMap<String, Int>
        get() = LinkedHashMap(_items)
        set(value) {
            _items = LinkedHashMap(value)
        }
    private var _items: MutableMap<String, Int> = LinkedHashMap()

    /**
     * Empty constructor; needed for Firebase to serialize the result of a query
     */
    constructor() {}

    constructor(key: String? = null, items: MutableMap<String, Int>) {
        this.key = key
        this.items = items
    }

    override fun updateItem(itemKey: String, quantity: Int) {
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
}