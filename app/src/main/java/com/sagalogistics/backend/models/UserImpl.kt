package com.sagalogistics.backend.models

import android.os.Parcel
import android.os.Parcelable

class UserImpl : User, Parcelable{
    companion object CREATOR : Parcelable.Creator<UserImpl> {
        override fun createFromParcel(parcel: Parcel): UserImpl {
            return UserImpl(parcel)
        }

        override fun newArray(size: Int): Array<UserImpl?> {
            return arrayOfNulls(size)
        }
    }

    override var key: String? = null
    override lateinit var name: String
    override var bars: MutableList<String>
        get() = ArrayList(_bars)
        set(value) {
            _bars = ArrayList(value)
        }
    private var _bars: MutableList<String> = ArrayList()

    constructor() {}

    constructor(key: String? = null, name: String, bars: MutableList<String>){
        this.key = key
        this.name = name
        this._bars = bars
    }

    override fun addBar(barKey: String) {
        _bars.add(barKey)
    }

    override fun removeBar(barKey: String) {
        _bars.remove(barKey)
    }

    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        parcel.readList(_bars, MutableList::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeList(bars)
    }

    override fun describeContents(): Int {
        return 0
    }
}