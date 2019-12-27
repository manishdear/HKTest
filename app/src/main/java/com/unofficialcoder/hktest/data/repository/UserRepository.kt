package com.unofficialcoder.hktest.data.repository

import com.unofficialcoder.hktest.data.local.DatabaseService
import com.unofficialcoder.hktest.data.local.prefs.UserPreferences
import com.unofficialcoder.hktest.data.model.User
import com.unofficialcoder.hktest.data.remote.NetworkService
import com.unofficialcoder.hktest.data.remote.request.LoginRequest
import com.unofficialcoder.hktest.di.LoginNetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @LoginNetworkService
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: User) {

        userPreferences.setUserName(user.name)
        userPreferences.setUserEmail(user.email)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserName()
        userPreferences.removeUserEmail()
    }

    fun getCurrentUser(): User? {

        val userName = userPreferences.getUserName()
        val userEmail = userPreferences.getUserEmail()

        return if ( userName != null && userEmail != null )
            User(userName, userEmail)
        else
            null
    }

    fun doUserLogin(type: String, email: String, password: String): Single<User> =
        networkService.doLoginCall(LoginRequest(type, email, password))
            .map {
                User(
                    it.data.name,
                    it.data.email
                )
            }
}