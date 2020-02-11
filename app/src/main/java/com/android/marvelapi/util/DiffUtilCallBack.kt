package com.android.marvelapi.util

import androidx.recyclerview.widget.DiffUtil
import com.android.marvelapi.model.Character

class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
                && oldItem.id == newItem.id
    }

}