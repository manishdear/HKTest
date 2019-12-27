package com.unofficialcoder.hktest.data.local

import android.content.Context
import com.unofficialcoder.hktest.di.ApplicationContext
import com.unofficialcoder.hktest.di.DatabaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseService @Inject constructor(
    @ApplicationContext private val context: Context,
    @DatabaseInfo private val databaseName: String,
    @DatabaseInfo private val version: Int)// do the initialisation here
{

    val dummyData: String
        get() = "DATABASE_DUMMY_DATA"
}
