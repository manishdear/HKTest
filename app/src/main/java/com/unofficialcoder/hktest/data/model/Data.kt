package com.unofficialcoder.hktest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(

    @Expose
    @SerializedName("id")
    var id : Int,

    @Expose
    @SerializedName("api_token")
    var api_token: String,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("mobile")
    var mobile: String,

    @Expose
    @SerializedName("outlets")
    var outlets: Int,

    @Expose
    @SerializedName("email_verified_at")
    var email_verified_at: String,

    @Expose
    @SerializedName("user_type")
    var user_type: String,

    @Expose
    @SerializedName("created_at")
    var created_at: String,

    @Expose
    @SerializedName("updated_at")
    var updated_at: String,

    @Expose
    @SerializedName("deleted_at")
    var deleted_at: String,

    @Expose
    @SerializedName("time")
    var time: String
)