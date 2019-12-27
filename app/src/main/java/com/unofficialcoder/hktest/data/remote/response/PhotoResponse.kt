package com.unofficialcoder.hktest.data.remote.response

import android.provider.ContactsContract
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.unofficialcoder.hktest.data.model.Photo

data class PhotoResponse(
    @Expose
    @SerializedName("data")
    val data: ArrayList<Photo>
)