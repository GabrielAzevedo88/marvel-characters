package com.android.marvelapi.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.marvelapi.R
import com.bumptech.glide.Glide

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("loadImageFromUrl")
    fun loadImageFromUrl(imageView: ImageView?, imageUrl: String?) {
        imageView?.run {
            Glide
                .with(this.context)
                .load(imageUrl.orEmpty())
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(this)
        }
    }
}
