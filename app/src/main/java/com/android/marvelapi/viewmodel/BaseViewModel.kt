package com.android.marvelapi.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.marvelapi.internal.State

open class BaseViewModel : ViewModel() {

    val state: MutableLiveData<State> = MutableLiveData()
    val mainLoaderVisibility = MutableLiveData<Int>().apply { value = View.VISIBLE }
    val errorVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    val successVisibility = MutableLiveData<Int>().apply { value = View.GONE }
    val toolbarVisibility = MutableLiveData<Int>().apply { value = View.GONE }

    fun getVisibility(show: Boolean = true): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    fun setState(state: State) {
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

        successVisibility.value =
            when (state) {
                State.SUCCESS -> getVisibility()
                State.LOADING, State.ERROR -> getVisibility(false)
            }

        toolbarVisibility.value =
            when (state) {
                State.SUCCESS, State.ERROR -> getVisibility()
                State.LOADING -> getVisibility(false)
            }
    }

}