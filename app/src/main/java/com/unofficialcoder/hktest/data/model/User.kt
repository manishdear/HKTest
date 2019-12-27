package com.unofficialcoder.hktest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @Expose
    @SerializedName("userName")
    val name: String,

    @Expose
    @SerializedName("userEmail")
    val email: String
)