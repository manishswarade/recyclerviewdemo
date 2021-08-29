package com.manishwarade.recyclerViewDemo.helper

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso

fun AppCompatImageView.load(link: String) {

    Picasso.get().load(link)
        .fit()
        .into(this)
}