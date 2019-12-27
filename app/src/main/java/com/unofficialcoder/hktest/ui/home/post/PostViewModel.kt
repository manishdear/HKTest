package com.unofficialcoder.hktest.ui.home.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.repository.PostRepository
import com.unofficialcoder.hktest.ui.base.BaseViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val postRepository: PostRepository
) : BaseViewModel( compositeDisposable, networkHelper) {

    val postLivedata: MutableLiveData<List<Post>> = MutableLiveData()

    val postLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getDummies(): LiveData<List<Post>> = postLivedata

    override fun onCreate() {
//        if (checkInternetConnectionWithMessage()) {
//            postLoading.postValue(true)
//            compositeDisposable.add(
//                postRepository.fetchPost()
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(
//                        {
//                            postLivedata.postValue(it)
//                            postLoading.postValue(false)
//                        },
//                        {
//                            Log.d("photo call error", it.toString())
//                            postLoading.postValue(false)
//                        })
//            )
//        }
    }

    fun postData(post: ArrayList<Post>){
        postLivedata.postValue(post)
    }

    fun postStatus(status: Boolean){
        postLoading.postValue(status)
    }


}