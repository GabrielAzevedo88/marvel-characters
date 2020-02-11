package com.android.marvelapi.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.BR
import com.android.marvelapi.extensions.fullImagePath
import com.android.marvelapi.model.CharacterSummary

class CharacterItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(item: CharacterSummary) {
        binding.apply {
            item.run {
                setVariable(BR.imageUrl, thumbnail.fullImagePath())
                setVariable(BR.name, name)
            }

            executePendingBindings()
        }
    }
}
