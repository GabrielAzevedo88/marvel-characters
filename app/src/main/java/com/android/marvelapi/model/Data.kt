package com.android.marvelapi.model

import com.squareup.moshi.Json

data class Data<T>(
    @field:Json(name = "results")
    val characters: List<T>
)
