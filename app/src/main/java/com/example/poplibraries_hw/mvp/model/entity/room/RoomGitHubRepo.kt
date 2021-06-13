package com.example.poplibraries_hw.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )

    ]
)
data class RoomGitHubRepo(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val forksCount: String,
    val userId: String
)