package com.android.marvelapi.repository

import androidx.paging.PageKeyedDataSource
import com.android.marvelapi.internal.State
import com.android.marvelapi.model.Character
import com.android.marvelapi.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//TODO: Ajustar para poder utilizar, não está atualizando o LiveData
class MarvelServiceDataSource(
    private val scope: CoroutineScope,
    private val repository: MarvelDataRepository,
    private val baseViewModel: BaseViewModel
) :
    PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        scope.launch {

            baseViewModel.setState(State.LOADING)

            try {

                val nextPage = 2

                val response = repository.getCharacters()

                response.takeIf { it.isSuccessful }?.run {
                    val list = this.body()?.data?.characters as List<Character>

                    callback.onResult(list, null, nextPage)
                    baseViewModel.setState(State.SUCCESS)
                }

            } catch (e: Exception) {
                baseViewModel.setState(State.ERROR)
                callback.onError(e)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        scope.launch {
            try {

                val currentPage = params.key
                val nextPage = currentPage + 1

                val response = repository.getCharacters()

                response.takeIf { it.isSuccessful }?.run {
                    val list = this.body()?.data?.characters as List<Character>

                    callback.onResult(list, nextPage)
                }

            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        /** nothing **/
    }
}