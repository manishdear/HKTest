package com.unofficialcoder.hktest.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoRequest(
    @Expose
    @SerializedName("id")
    var id: String
)