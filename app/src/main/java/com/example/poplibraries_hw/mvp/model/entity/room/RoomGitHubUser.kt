package com.example.poplibraries_hw.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser(
    @PrimaryKey var id:String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)