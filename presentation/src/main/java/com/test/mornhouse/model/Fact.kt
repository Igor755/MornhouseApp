package com.test.mornhouse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Fact(
    val id: Long? = null,
    val text: String? = null
) : Parcelable