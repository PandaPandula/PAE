package com.sagalogistics.backend.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude

class BarImpl : Bar, Parcelable{
    companion object CREATOR : Parcelable.Creator<BarImpl> {
        override fun createFromParcel(parcel: Parcel): BarImpl {
            return BarImpl(parcel)
        }

        override fun newArray(size: Int): Array<BarImpl?> {
            return arrayOfNulls(size)
        }
    }

    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override lateinit var name: String
    override var orders: MutableList<String>
        get() = ArrayList(_orders)
        set(value) {
            _orders = ArrayList(value)
        }
    private var _orders: MutableList<String> = ArrayList()

    constructor() {}

    override fun addOrder(orderKey: String) {
        _orders.add(orderKey)
    }

    override fun removeOrder(orderKey: String) {
        _orders.remove(orderKey)
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        parcel.readList(_orders, MutableList::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeList(orders)
    }

    override fun describeContents(): Int {
        return 0
    }
}