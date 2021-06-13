package com.example.poplibraries_hw.mvp.model.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun isOnline():Observable<Boolean>
    fun isOnlineSingle():Single<Boolean>
}