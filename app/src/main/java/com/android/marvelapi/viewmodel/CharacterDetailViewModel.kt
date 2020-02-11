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
    val noComicsVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    val comicsListVisibility = MutableLiveData<Int>().apply { value = View.VISIBLE }

    fun getCharacterDetail(id: Int) {
        setState(State.LOADING)

        viewModelScope.launch {
            try {

                repository.getCharacter(id).run {

                    takeIf { this.isSuccessful }?.run {
                        character.value = this.body()?.data?.characters?.first()

                        setState(State.SUCCESS)
                    } ?: setState(State.ERROR)

                    validateUi()
                }

            } catch (e: Exception) {
                setState(State.ERROR)
            }
        }
    }

    private fun validateUi() {
        character.value?.run {
            descVisibility.value = getVisibility(description.isNotBlank())
            noComicsVisibility.value = getVisibility(comics.items.isEmpty())
            comicsListVisibility.value = getVisibility(comics.items.isNotEmpty())
        }
    }

}