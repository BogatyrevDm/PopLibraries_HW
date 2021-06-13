package com.example.poplibraries_hw.mvp.model.image.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.poplibraries_hw.mvp.model.image.room.RoomImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image:RoomImage):Completable

    @Query("SELECT * FROM RoomImage WHERE  url = :url LIMIT 1")
    fun findByUrl(url:String): Single<RoomImage>
}