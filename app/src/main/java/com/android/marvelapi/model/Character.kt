package com.android.marvelapi.model

import android.os.Parcelable
import com.android.marvelapi.extensions.fullImagePath
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
) : Parcelable {

    fun getImageUrl(): String = thumbnail.fullImagePath()

}