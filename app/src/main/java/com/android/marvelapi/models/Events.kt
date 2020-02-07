package com.android.marvelapi.models

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemDetail>,
    val returned: Int
)