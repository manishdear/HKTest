package com.unofficialcoder.hktest.data.remote

import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.remote.request.LoginRequest
import com.unofficialcoder.hktest.data.remote.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @POST(Endpoints.LOGIN)
    fun doLoginCall(
        @Body request: LoginRequest
    ): Single<LoginResponse>



    @GET(Endpoints.PHOTO)
    fun doPhotoCall(
    ): Single<ArrayList<Photo>>


    @GET(Endpoints.POST)
    fun doPostCall(): Single<ArrayList<Post>>

    @GET(Endpoints.POSTBYID)
    fun doPostCallById(position: Int) : Single<Post>
}