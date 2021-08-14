package com.watchurmovie.movieone.dagger.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide



@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(it).centerCrop().into(imageView)
    }
}
@BindingAdapter("imageUrlRounded")
fun loadImageRound(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(it).circleCrop().into(imageView)
    }
}

@BindingConversion
fun setVisibility(state: Boolean): Int {
    return if (state) View.VISIBLE else View.GONE
}