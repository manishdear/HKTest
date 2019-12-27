package com.unofficialcoder.hktest.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.unofficialcoder.hktest.data.local.prefs.UserPreferences
import com.unofficialcoder.hktest.data.repository.PhotoRepository
import com.unofficialcoder.hktest.data.repository.UserRepository
import com.unofficialcoder.hktest.di.ActivityScope
import com.unofficialcoder.hktest.ui.base.BaseViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) {

    var userName : MutableLiveData<String> = MutableLiveData()

    val userEmail : MutableLiveData<String> = MutableLiveData()

    val logout: MutableLiveData<Boolean> = MutableLiveData()

    val onBack: MutableLiveData<Boolean> = MutableLiveData()

    fun onCreate() {

        val user = userRepository.getCurrentUser()

        if(user?.name != null){
            userName.postValue(user.name)
        }

        if(user?.email != null){
            userEmail.postValue(user.email)
        }

    }

    fun backPress(){
        if(userRepository.getCurrentUser() != null){
            onBack.postValue(true)
        }
    }

    fun onLogout(){
        userRepository.removeCurrentUser()
        logout.postValue(true)
    }

}