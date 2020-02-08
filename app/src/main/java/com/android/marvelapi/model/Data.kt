package com.android.marvelapi.model

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val characters: List<Character>,
    val total: Int
)