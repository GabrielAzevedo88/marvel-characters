package com.android.marvelapi.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun AppCompatActivity.bindingContentView(layout: Int): ViewDataBinding =
    DataBindingUtil.setContentView(this, layout)
