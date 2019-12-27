package com.unofficialcoder.hktest.utils

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException

import com.unofficialcoder.hktest.di.ApplicationContext
import java.io.IOException
import java.net.ConnectException

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(
    // Should be Application Context
    @ApplicationContext private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }


}
