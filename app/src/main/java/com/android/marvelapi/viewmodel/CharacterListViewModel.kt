package com.android.marvelapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.marvelapi.model.Character
import com.android.marvelapi.repository.MarvelDataRepository
import java.lang.Exception
import kotlinx.coroutines.*

class CharacterListViewModel(val repository: MarvelDataRepository) : ViewModel() {

    val characterList: MutableLiveData<List<Character>> = MutableLiveData()

    fun getList() {
        viewModelScope.launch {
            try {

                repository.getCharacters().let { response ->
                    characterList.value = response.data.characters
                }
                Log.e("success", "")
            } catch (e: Exception) {
                Log.e("error", "")
            } finally {
                Log.e("error", "")
            }
        }
    }
}
