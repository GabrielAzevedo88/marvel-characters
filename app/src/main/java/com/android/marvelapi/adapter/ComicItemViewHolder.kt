package com.android.marvelapi.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.BR
import com.android.marvelapi.model.ItemDetail

class ComicItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(item: ItemDetail) {
        binding.apply {
            item.run {
                setVariable(BR.title, name)
            }

            executePendingBindings()
        }
    }

}