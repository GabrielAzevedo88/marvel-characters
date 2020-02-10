package com.android.marvelapi.repository

import com.android.marvelapi.model.Character
import com.android.marvelapi.model.ResponseData

class MarvelDataRepository(private val service: MarvelService) : MarvelService {
    override suspend fun getCharacters(): ResponseData = service.getCharacters()
    override suspend fun getCharacter(id: Int): ResponseData = service.getCharacter(id)
}
