package com.android.marvelapi.repository

import com.android.marvelapi.model.Character
import com.android.marvelapi.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(): ResponseData

    @GET("characters/{characterId}")
    suspend fun getCharacter(@Query("characterId") id: String): Character
}
