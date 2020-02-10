package com.android.marvelapi.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.marvelapi.internal.State
import com.android.marvelapi.model.Character
import com.android.marvelapi.repository.MarvelDataRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(val repository: MarvelDataRepository) : BaseViewModel() {

    var character: MutableLiveData<Character> = MutableLiveData()
    val descVisibility = MutableLiveData<Int>().apply { value = View.GONE }

    fun getCharacterDetail(id: Int) {
        setState(State.LOADING)

        viewModelScope.launch {
            try {

                repository.getCharacter(id).run {
                    character.value = this.data.characters.first()
                    validateUi()

                    setState(State.SUCCESS)
                }

            } catch (e: Exception) {
                setState(State.ERROR)
            }
        }
    }

    fun validateUi() {
        character.value?.run {
            descVisibility.value = getVisibility(description.isNotBlank())
        }
    }

}