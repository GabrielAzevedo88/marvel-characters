package com.android.marvelapi.extensions

import com.android.marvelapi.model.Thumbnail

fun Thumbnail.fullImagePath(): String = this.run { "$path.$extension" }
