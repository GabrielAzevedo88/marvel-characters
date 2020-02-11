package com.android.marvelapi.view.activities

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.BR
import com.android.marvelapi.R
import com.android.marvelapi.adapter.CharacterItemAdapter
import com.android.marvelapi.extensions.bindingContentView
import com.android.marvelapi.extensions.observe
import com.android.marvelapi.internal.State
import com.android.marvelapi.util.AppRouter
import com.android.marvelapi.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    companion object {
        private const val NUMBER_COLUMNS = 2
        private const val RECYCLER_STATE = "recyclerState"
    }

    private val viewModel: CharacterListViewModel by viewModel()
    private var characterAdapter: CharacterItemAdapter? = null
    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupAdapter()
        setupRecycler()
        setupObservable()

        getCharacterList()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            RECYCLER_STATE,
            aCharacterList_rvCharacters.layoutManager?.onSaveInstanceState()
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        recyclerState = savedInstanceState.getParcelable(RECYCLER_STATE)
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_main).run {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@CharacterListActivity
        }
    }

    private fun setupAdapter() {
        characterAdapter = CharacterItemAdapter(AppRouter(this@CharacterListActivity))
    }

    private fun setupRecycler() {
        val layManager = GridLayoutManager(this@CharacterListActivity, NUMBER_COLUMNS)

        aCharacterList_rvCharacters.apply {
            adapter = characterAdapter
            layoutManager = layManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    layManager.findLastVisibleItemPosition().run {
                        adapter?.let {
                            if (this == it.itemCount - 1 && viewModel.state.value != State.LOADING) {
                                viewModel.getList(true)
                            }
                        }
                    }
                }
            })
        }
    }

    private fun setupObservable() {
        viewModel.run {
            observe(characterList) { list ->
                list?.let {
                    restoreRecyclerState()
                    characterAdapter?.setList(it)
                }
            }
        }
    }

    private fun getCharacterList() {
        viewModel.getList()
    }

    private fun restoreRecyclerState() {
        recyclerState?.let {
            aCharacterList_rvCharacters.layoutManager?.onRestoreInstanceState(recyclerState)
        }
    }
}
