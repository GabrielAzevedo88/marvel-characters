package com.android.marvelapi.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.marvelapi.BR
import com.android.marvelapi.R
import com.android.marvelapi.adapter.ComicItemAdapter
import com.android.marvelapi.extensions.bindingContentView
import com.android.marvelapi.extensions.observe
import com.android.marvelapi.internal.Constants.Companion.EXTRA_CHARACTER_ID
import com.android.marvelapi.model.Character
import com.android.marvelapi.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.content_character_detail.*
import kotlinx.android.synthetic.main.layout_appbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModel()
    private val characterId: Int by lazy {
        intent.getIntExtra(EXTRA_CHARACTER_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupActionBar()
        setupObservable()
        getData()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_character_detail).run {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@CharacterDetailActivity
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(lAppbar_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lAppbar_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupObservable() {
        viewModel.run {
            observe(character) {
                it?.let {
                    setupAdapter(it)
                }
            }
        }
    }

    private fun setupAdapter(character: Character) {
        character.run {
            setupRecycler(ComicItemAdapter(comics.items))
        }
    }

    private fun setupRecycler(adapter: ComicItemAdapter) {
        aCharacterDetail_rvComics.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@CharacterDetailActivity)
        }
    }

    private fun getData() {
        viewModel.getCharacterDetail(characterId)
    }

}