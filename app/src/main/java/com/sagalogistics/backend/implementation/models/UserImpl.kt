package com.sagalogistics.backend.implementation.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.sagalogistics.backend.api.models.User

class UserImpl : User, Parcelable{
    @get:Exclude
    @set:Exclude
    override var key: String? = null
    override lateinit var name: String
    override var bars: MutableList<String>
        get() = ArrayList(_bars)
        set(value) {
            _bars = ArrayList(value)
        }
    private var _bars: MutableList<String> = ArrayList()

    constructor() {}

    constructor(key: String? = null, name: String, bars: MutableList<String> = ArrayList()){
        this.key = key
        this.name = name
        this.bars = bars
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
        parcel.readList(bars, MutableList::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeList(bars)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserImpl> {
        override fun createFromParcel(parcel: Parcel): UserImpl {
            return UserImpl(parcel)
        }

        override fun newArray(size: Int): Array<UserImpl?> {
            return arrayOfNulls(size)
        }
    }
}