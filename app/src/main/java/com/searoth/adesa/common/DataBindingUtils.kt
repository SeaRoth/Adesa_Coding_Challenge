package com.searoth.adesa.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.searoth.adesa.R
import com.searoth.adesa.di.SeaRothServiceLocator
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:imageUrl", "app:placeholder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, placeholder: Drawable) {
    SeaRothServiceLocator.resolve(Picasso::class.java)
        .load(url)
        .placeholder(placeholder)
        .error(R.drawable.ic_404)
        .into(imageView)
}