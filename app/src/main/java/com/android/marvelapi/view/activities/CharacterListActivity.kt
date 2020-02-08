package com.android.marvelapi.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.marvelapi.R
import com.android.marvelapi.viewmodel.CharacterListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getList()
    }
}
