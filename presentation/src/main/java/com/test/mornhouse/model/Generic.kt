package com.test.mornhouse.model

import android.os.Parcel
import android.os.Parcelable

data class Generic(
    var id: Long?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readValue(Long::class.java.classLoader) as? Long)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Generic> {
        override fun createFromParcel(parcel: Parcel): Generic {
            return Generic(parcel)
        }

        override fun newArray(size: Int): Array<Generic?> {
            return arrayOfNulls(size)
        }
    }
}