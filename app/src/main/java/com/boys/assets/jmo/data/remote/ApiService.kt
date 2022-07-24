package com.boys.assets.jmo.data.remote

import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("/posts")
    suspend fun getPosts(): List<PostsModel>

}