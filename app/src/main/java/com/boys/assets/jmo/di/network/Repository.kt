package com.boys.assets.jmo.di.network

import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel

interface Repository {

    suspend fun getPosts() : List<PostsModel>

}