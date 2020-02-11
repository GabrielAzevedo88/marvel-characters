package com.android.marvelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.R
import com.android.marvelapi.model.ItemDetail

class ComicItemAdapter(
    private val comics: List<ItemDetail>
) : RecyclerView.Adapter<ComicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicItemViewHolder =
        ComicItemViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_comic_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ComicItemViewHolder, position: Int) {
        val comic = comics[position]

        holder.binding(comic)
    }

    override fun getItemCount(): Int = comics.count()
}
