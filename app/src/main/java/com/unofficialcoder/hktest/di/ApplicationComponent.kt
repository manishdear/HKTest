package com.unofficialcoder.hktest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.unofficialcoder.hktest.HKTestApplication
import com.unofficialcoder.hktest.data.local.DatabaseService
import com.unofficialcoder.hktest.data.local.prefs.UserPreferences
import com.unofficialcoder.hktest.data.remote.NetworkService
import com.unofficialcoder.hktest.data.repository.PhotoRepository
import com.unofficialcoder.hktest.data.repository.PostRepository
import com.unofficialcoder.hktest.data.repository.UserRepository
import com.unofficialcoder.hktest.utils.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: HKTestApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    @com.unofficialcoder.hktest.di.NetworkService
    fun getNetworkService(): NetworkService

    @LoginNetworkService
    fun getLoginNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getPhotoRepository(): PhotoRepository

    fun getPostRepository(): PostRepository

    fun getUserRepository(): UserRepository

    fun getUserPrefrence(): UserPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getCompositeDisposable(): CompositeDisposable


}
