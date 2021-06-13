package com.example.poplibraries_hw.mvp.model.image.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomImage(
    @PrimaryKey var url:String,
    var path: String
)