package com.android.marvelapi.di

import com.android.marvelapi.repository.MarvelAPI
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val retrofitModule = module {
    single {
        MarvelAPI.getMarvelApiService()
    }
}

fun loadMarvelKoinModules() {
    loadKoinModules(
        listOf(
            retrofitModule
        )
    )
}