package com.unofficialcoder.hktest.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class DatabaseInfo

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class NetworkInfo

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class LoginNetworkService

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class NetworkService