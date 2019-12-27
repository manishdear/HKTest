package com.unofficialcoder.hktest.ui.base

import androidx.lifecycle.MutableLiveData
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T : Any>(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel( compositeDisposable, networkHelper) {

    val data: MutableLiveData<T> = MutableLiveData()

    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}