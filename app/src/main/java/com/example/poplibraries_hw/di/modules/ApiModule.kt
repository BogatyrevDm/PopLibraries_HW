package com.example.poplibraries_hw.di.modules

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.poplibraries_hw.mvp.model.api.IDataSource
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import com.example.poplibraries_hw.ui.App
import com.example.poplibraries_hw.ui.network.AndroidNetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Named("baseUrl")
    @Provides
    fun baseURL() = "https://api.github.com"

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .create()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Singleton
    @Provides
    fun networkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)
}