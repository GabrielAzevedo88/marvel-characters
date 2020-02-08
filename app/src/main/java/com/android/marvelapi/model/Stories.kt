package com.android.marvelapi.model

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemDetail>,
    val returned: Int
)