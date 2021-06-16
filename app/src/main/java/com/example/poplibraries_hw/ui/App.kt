package com.example.poplibraries_hw.ui

import android.app.Application
import com.example.poplibraries_hw.di.AppComponent
import com.example.poplibraries_hw.di.DaggerAppComponent
import com.example.poplibraries_hw.di.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
        val component get() = instance.appComponent
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}