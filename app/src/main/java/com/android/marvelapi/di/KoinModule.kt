package com.android.marvelapi.di

import com.android.marvelapi.repository.MarvelAPI
import com.android.marvelapi.repository.MarvelDataRepository
import com.android.marvelapi.viewmodel.CharacterDetailViewModel
import com.android.marvelapi.viewmodel.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val serviceModule = module {
    single {
        MarvelAPI.getMarvelApiService()
    }
}

private val repositoryModule = module {
    single {
        MarvelDataRepository(service = get())
    }
}

private val viewModelModule = module {
    viewModel { CharacterListViewModel(repository = get()) }
    viewModel { CharacterDetailViewModel(repository = get()) }
}

fun loadMarvelKoinModules() {
    loadKoinModules(
        listOf(
            serviceModule,
            repositoryModule,
            viewModelModule
        )
    )
}
