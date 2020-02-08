package com.android.marvelapi.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @field:Json(name = "results")
    val characters: List<Character>,
    val total: Int
) : Parcelable
