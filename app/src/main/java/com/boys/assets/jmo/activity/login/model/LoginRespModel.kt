package com.boys.assets.jmo.activity.login.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRespModel (

    @SerializedName("token")
    val token: String?

)