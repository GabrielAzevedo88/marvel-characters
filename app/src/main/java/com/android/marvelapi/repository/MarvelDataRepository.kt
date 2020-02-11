package com.android.marvelapi.repository

import com.android.marvelapi.model.Character
import com.android.marvelapi.model.CharacterSummary
import com.android.marvelapi.model.ResponseData
import retrofit2.Response

class MarvelDataRepository(private val service: MarvelService) : MarvelService {
    override suspend fun getCharacters(offset: Int): Response<ResponseData<CharacterSummary>> =
        service.getCharacters(offset)

    override suspend fun getCharacter(id: Int): Response<ResponseData<Character>> =
        service.getCharacter(id)
}
