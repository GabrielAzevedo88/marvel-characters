package com.android.marvelapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.marvelapi.internal.State
import com.android.marvelapi.model.CharacterSummary
import com.android.marvelapi.repository.MarvelDataRepository
import kotlinx.coroutines.launch

class CharacterListViewModel(val repository: MarvelDataRepository) : BaseViewModel() {

    companion object {
        private const val OFFSET = 20
    }

    var page: Int = 0
    val characterList: MutableLiveData<List<CharacterSummary>> = MutableLiveData()

    fun getList(loadingMore: Boolean = false) {

        takeIf { loadingMore }?.run { page += 1 } ?: run { page = 0 }

        setState(State.LOADING)

        viewModelScope.launch {
            try {

                repository.getCharacters(getOffsetValue(page)).run {

                    takeIf { this.isSuccessful }?.run {
                        val characters = this.body()?.data?.characters

                        characters?.let {
                            characterList.value = it
                            setState(State.SUCCESS)
                        } ?: setState(State.ERROR)
                    } ?: setState(State.ERROR)
                }
            } catch (e: Exception) {
                setState(State.ERROR)
            }
        }
    }

    fun getOffsetValue(page: Int): Int = (page * OFFSET)

    // TODO: Usar quando arrumar o pagging
//    lateinit var postsLiveData: LiveData<PagedList<Character>>
//    fun getList() {
//        val config = PagedList.Config.Builder()
//            .setPageSize(20)
//            .setEnablePlaceholders(true)
//            .build()
//        postsLiveData = initializedPagedListBuilder(config).build()
//    }
//
//    private fun initializedPagedListBuilder(config: PagedList.Config):
//            LivePagedListBuilder<Int, Character> {
//
//        val dataSourceFactory = object : DataSource.Factory<Int, Character>() {
//            override fun create(): DataSource<Int, Character> {
//                return MarvelServiceDataSource(
//                    viewModelScope,
//                    repository,
//                    this@CharacterListViewModel
//                )
//            }
//        }
//        return LivePagedListBuilder<Int, Character>(dataSourceFactory, config)
//    }
}
