package com.example.poplibraries_hw.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser
import com.example.poplibraries_hw.mvp.model.entity.room.dao.RepoDao
import com.example.poplibraries_hw.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGitHubUser::class, RoomGitHubRepo::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepoDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created")
        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .build()
            }
        }

    }
}