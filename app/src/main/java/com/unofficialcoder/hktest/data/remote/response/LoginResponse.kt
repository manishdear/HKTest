package com.unofficialcoder.hktest.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.unofficialcoder.hktest.data.model.Data

data class LoginResponse(

    @Expose
    @SerializedName("return")
    var return1 : Boolean,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("data")
    var data: Data

)