package com.unofficialcoder.hktest.di

import com.unofficialcoder.hktest.ui.home.HomeActivity
import com.unofficialcoder.hktest.ui.login.LoginActivity
import com.unofficialcoder.hktest.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: LoginActivity)

    fun inject(activity: HomeActivity)

    fun inject(activity: SplashActivity)
}
