package com.android.marvelapi.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.BR
import com.android.marvelapi.model.Character

class CharacterItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(item: Character) {
        binding.apply {
            item.run {
                setVariable(BR.imageUrl, getImageUrl())
                setVariable(BR.name, name)
            }

            executePendingBindings()
        }
    }

}