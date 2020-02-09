package com.android.marvelapi.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.marvelapi.internal.State
import com.android.marvelapi.model.Character
import com.android.marvelapi.repository.MarvelDataRepository
import java.lang.Exception
import kotlinx.coroutines.*

class CharacterListViewModel(val repository: MarvelDataRepository) : ViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    val mainLoaderVisibility = MutableLiveData<Int>().apply { value = View.VISIBLE }
    val errorVisibility = MutableLiveData<Int>().apply { value = View.GONE }

    private fun getVisibility(show: Boolean = true): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    private fun setState(state: State) {
        mainLoaderVisibility.value =
            when (state) {
                State.LOADING -> getVisibility()
                State.SUCCESS, State.ERROR -> getVisibility(false)
            }

        errorVisibility.value =
            when (state) {
                State.LOADING, State.SUCCESS -> getVisibility(false)
                State.ERROR -> getVisibility(true)
            }
    }

    fun getList() {
        setState(State.LOADING)

        viewModelScope.launch {
            try {

                repository.getCharacters().run {
                    characterList.value = this.data.characters

                    setState(State.SUCCESS)
                }

            } catch (e: Exception) {
                setState(State.ERROR)
            }
        }
    }
}
