package com.unofficialcoder.hktest.data.repository

import com.unofficialcoder.hktest.data.local.DatabaseService
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.remote.NetworkService
import io.reactivex.Single
import java.text.FieldPosition
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    @com.unofficialcoder.hktest.di.NetworkService
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchPost(): Single<List<Post>> =
        networkService.doPostCall()
            .map {
                it
            }

    fun fetchPostById(position: Int) : Single<Post> =
        networkService.doPostCallById(position)
            .map {
                it
            }
}