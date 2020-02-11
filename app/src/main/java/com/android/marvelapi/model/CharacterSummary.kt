package com.android.marvelapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterSummary(
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail
) : Parcelable
