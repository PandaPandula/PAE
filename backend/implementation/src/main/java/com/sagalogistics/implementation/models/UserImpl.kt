package com.sagalogistics.implementation.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.sagalogistics.lib.models.User

/**
 * Implementation of the [User] interface
 */
class UserImpl : User, Parcelable{
    /**
     * The key of the user in the database
     */
    @get:Exclude
    @set:Exclude
    override var key: String? = null

    /**
     * The name of the user
     */
    override lateinit var name: String

    /**
     * A [MutableList] with the
     * [keys of the bars][com.sagalogistics.lib.models.Bar.key] assigned to the user
     */
    override var bars: MutableList<String>
        get() = ArrayList(_bars)
        set(value) {
            _bars = ArrayList(value)
        }

    /**
     * A backing property holding the actual [MutableList]
     * of [bars][com.sagalogistics.lib.models.Bar] to make it true private;
     * that is, it can only be modified through the methods of [User]
     */
    private var _bars: MutableList<String> = ArrayList()

    /**
     * Empty constructor needed for serialization
     *
     * Should not be used, since a user is expected to have a [name]
     */
    constructor() {}

    /**
     * Standard constructor
     */
    constructor(key: String? = null, name: String, bars: MutableList<String> = ArrayList()){
        this.key = key
        this.name = name
        this.bars = bars
    }

    /**
     * Assigns a bar to the user
     */
    override fun addBar(barKey: String) {
        _bars.add(barKey)
    }

    /**
     * Removes a bar from the user's assignments
     */
    override fun removeBar(barKey: String) {
        _bars.remove(barKey)
    }

    /**
     * Parcel constructor used for [Parcelable] implementation
     */
    constructor(parcel: Parcel) {
        key = parcel.readString()
        name = parcel.readString()!!
        parcel.readList(bars, MutableList::class.java.classLoader)
    }

    /**
     * Implementation of [Parcelable.writeToParcel]
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(key)
        dest.writeString(name)
        dest.writeList(bars)
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
    companion object CREATOR : Parcelable.Creator<UserImpl> {
        /**
         * Creates a [UserImpl] from a [parcel]
         */
        override fun createFromParcel(parcel: Parcel): UserImpl {
            return UserImpl(parcel)
        }

        /**
         * Returns an empty array of [UserImpl] of a given [size]
         */
        override fun newArray(size: Int): Array<UserImpl?> {
            return arrayOfNulls(size)
        }
    }
}