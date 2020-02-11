package com.android.marvelapi.repository

import com.android.marvelapi.model.Character
import com.android.marvelapi.model.CharacterSummary
import com.android.marvelapi.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int = 1): Response<ResponseData<CharacterSummary>>

    @GET("characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") id: Int): Response<ResponseData<Character>>
}
