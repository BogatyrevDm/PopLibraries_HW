package com.example.poplibraries_hw.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser
import com.example.poplibraries_hw.mvp.model.entity.room.dao.RepoDao
import com.example.poplibraries_hw.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGitHubUser::class, RoomGitHubRepo::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepoDao
    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}