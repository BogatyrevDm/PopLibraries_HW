package com.example.poplibraries_hw.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.poplibraries_hw.mvp.model.image.IImageCache
import com.example.poplibraries_hw.mvp.model.image.IImageLoader
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Scheduler

class GlideImageLoader(
    val mainThreadScheduler: Scheduler,
    val networkStatus: INetworkStatus,
    val cache: IImageCache
) : IImageLoader<ImageView> {
    override fun loadInto(url: String, login: String, container: ImageView) {

        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                Glide.with(container.context)
                    .asBitmap()
                    .load(url)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            //Обработка провала загрузки
                            return false
                        }

                        override fun onResourceReady(
                            bitmap: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            cache.put(url, login, bitmap).subscribe()
                            //Делаем что-то с bitmap
                            return false
                        }
                    })
                    .into(container)
            } else {
                cache.get(url)
                    .observeOn(mainThreadScheduler)
                    .subscribe({
                        Glide.with(container.context)

                            .load(it)
                            .into(container)
                    }, {
                        println(it.message)
                    }
                    )
            }
        }
    }
}