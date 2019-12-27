package com.unofficialcoder.hktest.ui.home.photo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.unofficialcoder.hktest.data.local.DatabaseService
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.remote.NetworkService
import com.unofficialcoder.hktest.data.repository.PhotoRepository
import com.unofficialcoder.hktest.ui.base.BaseItemViewModel
import com.unofficialcoder.hktest.ui.base.BaseViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dummyRepository: PhotoRepository
) : BaseViewModel( compositeDisposable, networkHelper) {

    val photoLivedata: MutableLiveData<List<Photo>> = MutableLiveData()

    val photoLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getDummies(): LiveData<List<Photo>> = photoLivedata

    override fun onCreate() {
        if (checkInternetConnectionWithMessage()) {
            photoLoading.postValue(true)
            compositeDisposable.add(
                dummyRepository.fetchPhoto()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            photoLivedata.postValue(it)
                            photoLoading.postValue(false)
                        },
                        {
                            Log.d("photo call error", it.toString())
                            photoLoading.postValue(false)
                        })
            )
        }
    }


}