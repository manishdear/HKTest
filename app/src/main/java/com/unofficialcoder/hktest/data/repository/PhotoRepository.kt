package com.unofficialcoder.hktest.data.repository

import com.unofficialcoder.hktest.data.local.DatabaseService
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.remote.NetworkService
import com.unofficialcoder.hktest.di.LoginNetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    @com.unofficialcoder.hktest.di.NetworkService
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchPhoto(): Single<List<Photo>> =
        networkService.doPhotoCall()
            .map {
                it
            }
}