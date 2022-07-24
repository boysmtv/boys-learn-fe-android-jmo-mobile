package com.boys.assets.jmo.activity.menu.fragment.posts.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostsModel(

    @SerializedName("userId")
    val userId: String?,

    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String

)