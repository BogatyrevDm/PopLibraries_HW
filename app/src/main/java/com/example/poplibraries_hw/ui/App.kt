package com.example.poplibraries_hw.ui

import android.app.Application
import com.example.poplibraries_hw.mvp.model.entity.room.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }
}