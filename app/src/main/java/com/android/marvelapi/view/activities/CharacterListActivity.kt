package com.android.marvelapi.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.marvelapi.R
import com.android.marvelapi.adapter.CharacterItemAdapter
import com.android.marvelapi.extensions.observe
import com.android.marvelapi.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getList()

        viewModel.run {
            observe(characterList) {
                it?.run {
                    setupRecycler(CharacterItemAdapter(this))
                }
            }
        }
    }

    private fun setupRecycler(adapter: CharacterItemAdapter) {
        aCharacterList_rvCharacters.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this@CharacterListActivity, 2)
        }
    }
}
