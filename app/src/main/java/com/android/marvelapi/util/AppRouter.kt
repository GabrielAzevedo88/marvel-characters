package com.android.marvelapi.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.android.marvelapi.internal.Constants.Companion.EXTRA_CHARACTER_ID
import com.android.marvelapi.model.CharacterSummary
import com.android.marvelapi.view.activities.CharacterDetailActivity

class AppRouter(private val activity: AppCompatActivity) {

    fun goToDetail(item: CharacterSummary) {
        Intent(activity, CharacterDetailActivity::class.java).apply {
            putExtra(EXTRA_CHARACTER_ID, item.id)

            activity.startActivity(this)
        }
    }
}
