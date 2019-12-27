package com.unofficialcoder.hktest.di


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.unofficialcoder.hktest.HKTestApplication
import com.unofficialcoder.hktest.data.remote.NetworkService
import com.unofficialcoder.hktest.data.remote.Networking
import com.unofficialcoder.hktest.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: HKTestApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("bootcamp-instagram-project-prefs", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Provides
    @NetworkInfo
    fun provideApiKey(): String = "SOME_API_KEY"

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dummy_db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Singleton
    @com.unofficialcoder.hktest.di.NetworkService
    @Provides
    fun provideNetworkService(): NetworkService = Networking.create(
        "https://jsonplaceholder.typicode.com/"
    )

    @Singleton
    @LoginNetworkService
    @Provides
    fun provideLoginNetworkService(): NetworkService = Networking.create(
        "http://139.59.87.150/demo/Shree_Sai_Mall/"
    )
}