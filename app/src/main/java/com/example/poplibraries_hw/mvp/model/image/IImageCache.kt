package com.example.poplibraries_hw.mvp.model.image

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun put(url:String, login:String, image: Bitmap?):Completable
    fun get(url:String):Single<ByteArray>
}