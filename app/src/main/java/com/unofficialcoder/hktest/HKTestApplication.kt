package com.unofficialcoder.hktest

import android.app.Application
import com.unofficialcoder.hktest.di.ApplicationComponent
import com.unofficialcoder.hktest.di.ApplicationModule
import com.unofficialcoder.hktest.di.DaggerApplicationComponent

class HKTestApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}