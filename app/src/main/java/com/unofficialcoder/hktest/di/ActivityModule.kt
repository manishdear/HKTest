package com.unofficialcoder.hktest.di

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.unofficialcoder.hktest.data.repository.PostRepository
import com.unofficialcoder.hktest.data.repository.UserRepository
import com.unofficialcoder.hktest.ui.base.BaseActivity
import com.unofficialcoder.hktest.ui.home.post.PostViewModel
import com.unofficialcoder.hktest.ui.splash.SplashViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import com.unofficialcoder.hktest.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


}

