package com.sagalogistics.backend.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import android.os.Parcel
import android.os.Parcelable.Creator

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
            _items = value
        }
    private var _items: MutableMap<String, Int> = LinkedHashMap()

    constructor() {}

    constructor(key: String?, items: MutableMap<String, Int>) {
        this.key = key
        this._items = items
    }

    constructor(items: MutableMap<String, Int>) {
        this._items = items
    }

    override fun updateItem(key: String, quantity: Int) {
        _items[key] = quantity
    }

    override fun getQuantityItem(key: String): Int? {
        return _items[key]
    }

    override fun removeItem(key: String) {
        _items.remove(key)
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        parcel.readMap(_items, MutableMap::class.java.classLoader)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeMap(items)
    }
}