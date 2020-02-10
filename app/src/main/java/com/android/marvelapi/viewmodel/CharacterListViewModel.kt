package com.android.marvelapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.marvelapi.internal.State
import com.android.marvelapi.model.Character
import com.android.marvelapi.repository.MarvelDataRepository
import kotlinx.coroutines.launch

class CharacterListViewModel(val repository: MarvelDataRepository) : BaseViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

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
