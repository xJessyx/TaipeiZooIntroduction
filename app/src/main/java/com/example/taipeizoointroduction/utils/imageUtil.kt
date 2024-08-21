package com.example.taipeizoointroduction.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun loadImage(imgView: ImageView, url: String) {
    Glide.with(imgView.context)
    .load(url)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .format(DecodeFormat.PREFER_RGB_565)
    .error(android.R.drawable.ic_dialog_alert)
    .into(imgView)
}