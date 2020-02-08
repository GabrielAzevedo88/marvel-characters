package com.android.marvelapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDetail(
    val name: String?,
    val resourceURI: String,
    val type: String
) : Parcelable
