package com.example.poplibraries_hw.mvp.model.image

import android.content.Context
import android.graphics.Bitmap
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import com.example.poplibraries_hw.mvp.model.image.room.RoomImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class RoomImageCache(val context: Context) : IImageCache {
    private val db = Database.getInstance()
    override fun put(url: String, login: String, image: Bitmap?): Completable {
        val path = "$login.jpg"
        return Completable.fromAction {

            val file =
                File(context.getExternalFilesDir(null), path)
            FileOutputStream(file).use {
                image?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }

        }.subscribeOn(Schedulers.io())
            .andThen(db.imageDao.insert(RoomImage(url, path)))
    }

    override fun get(url: String): Single<ByteArray> =
        db.imageDao.findByUrl(url).flatMap {roomImage->
            Single.just(
                File(context.getExternalFilesDir(null), roomImage.path).inputStream().readBytes()
            )
    }.subscribeOn(Schedulers.io())
}