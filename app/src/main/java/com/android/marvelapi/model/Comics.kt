package com.android.marvelapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemDetail>,
    val returned: Int
) : Parcelable
