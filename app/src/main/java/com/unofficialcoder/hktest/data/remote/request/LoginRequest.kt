package com.unofficialcoder.hktest.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @Expose
    @SerializedName("type")
    var type :String,

    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("password")
    var password: String
)