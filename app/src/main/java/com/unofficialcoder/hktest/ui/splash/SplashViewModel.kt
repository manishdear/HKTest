package com.unofficialcoder.hktest.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unofficialcoder.hktest.data.repository.UserRepository
import com.unofficialcoder.hktest.di.ActivityScope
import com.unofficialcoder.hktest.ui.base.BaseViewModel
import com.unofficialcoder.hktest.utils.Event
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

@ActivityScope
class SplashViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
)  {

    val launchDummy: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    fun onCreate() {
        if (userRepository.getCurrentUser() != null)
            launchDummy.postValue(Event(Collections.emptyMap()))
        else
            launchLogin.postValue(Event(Collections.emptyMap()))
    }
}