package com.unofficialcoder.hktest.ui.home.post


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.repository.PostRepository
import com.unofficialcoder.hktest.ui.base.BaseItemViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostItemViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val postRepository: PostRepository
) : BaseItemViewModel<Post>( compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "DummyItemViewModel"
    }

    val title: LiveData<String> = Transformations.map(data) {
        it.title }

    val description: LiveData<String> = Transformations.map(data) {
        it.body
    }

    fun onItemClick(position: Int) {
        messageString.postValue("onItemClick at $position of ")
        Log.d(TAG, "onItemClick at $position")
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate called")
    }
}