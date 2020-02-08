package com.android.marvelapi.repository

import com.android.marvelapi.model.Character
import com.android.marvelapi.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(): ResponseData

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Query("characterId") id: String): Character
}
