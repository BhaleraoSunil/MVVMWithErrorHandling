package com.example.mvvmwitherrorhandling.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

class NetworkUtils {

    companion object{
        fun isInternetAvailable(context: Context): Boolean {
            val service = Context.CONNECTIVITY_SERVICE
            val manager = context.getSystemService(service) as ConnectivityManager?
            val network = manager?.activeNetworkInfo
            Log.d(javaClass.name, "hasNetworkAvailable: ${(network != null)}")
            return (network?.isConnected) ?: false
        }
    }
}