package com.example.poplibraries_hw.di.modules

import androidx.room.Room
import com.example.poplibraries_hw.mvp.model.cache.IUsersCache
import com.example.poplibraries_hw.mvp.model.cache.IUsersReposCache
import com.example.poplibraries_hw.mvp.model.cache.RoomUsersCache
import com.example.poplibraries_hw.mvp.model.cache.RoomUsersReposCache
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import com.example.poplibraries_hw.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUsersCache = RoomUsersCache(database)

    @Singleton
    @Provides
    fun usersReposCache(database: Database): IUsersReposCache = RoomUsersReposCache(database)

}