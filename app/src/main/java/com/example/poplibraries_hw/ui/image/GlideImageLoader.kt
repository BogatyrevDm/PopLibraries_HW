package com.example.poplibraries_hw.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.poplibraries_hw.mvp.model.image.IImageLoader

class GlideImageLoader() : IImageLoader<ImageView> {
    override fun loadInto(url: String, login: String, container: ImageView) {

        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}