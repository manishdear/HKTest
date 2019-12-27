package com.unofficialcoder.hktest.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.unofficialcoder.hktest.data.repository.UserRepository
import com.unofficialcoder.hktest.di.ActivityScope
import com.unofficialcoder.hktest.utils.Resource
import com.unofficialcoder.hktest.utils.Status
import com.unofficialcoder.hktest.utils.Validation
import com.unofficialcoder.hktest.utils.Validator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository) {

    val launchHome: MutableLiveData<Boolean> = MutableLiveData()

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()


    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }

    fun onLogin() {
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successValidation.size == validations.size) {
                loggingIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserLogin("a", email, password)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {
                                userRepository.saveCurrentUser(it)
                                loggingIn.postValue(false)
                                launchHome.postValue(true)
                                Log.i("LoginViewModle", userRepository.getCurrentUser().toString())
                            },
                            {
                                Log.d("LoginViewModle", "login error" + it.toString())
                                loggingIn.postValue(false)
                            }
                        )

                )

            }

        }

    }
}

