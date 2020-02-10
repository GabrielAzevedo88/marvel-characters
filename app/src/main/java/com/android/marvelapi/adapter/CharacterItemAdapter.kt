package com.android.marvelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelapi.R
import com.android.marvelapi.model.Character
import com.android.marvelapi.util.AppRouter

class CharacterItemAdapter(
    private val characters: List<Character>,
    private val router: AppRouter
) :
    RecyclerView.Adapter<CharacterItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder =
        CharacterItemViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_character_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val character = characters[position]

        holder.run {
            binding(character)

            itemView.setOnClickListener {
                router.goToDetail(character)
            }
        }
    }

    override fun getItemCount(): Int = characters.count()

}