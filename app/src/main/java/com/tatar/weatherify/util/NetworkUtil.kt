package com.tatar.weatherify.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil(private val context: Context) {

    fun hasInternetConnection(): Boolean {

        var isConnected = false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            isConnected = true
        }

        return isConnected
    }
}