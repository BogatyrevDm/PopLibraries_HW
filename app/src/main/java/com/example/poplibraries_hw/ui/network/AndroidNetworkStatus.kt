package com.example.poplibraries_hw.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.poplibraries_hw.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.subjects.BehaviorSubject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AndroidNetworkStatus(context: Context) : INetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline() = statusSubject

    override fun isOnlineSingle() = statusSubject.first(false)
}