package com.boys.assets.jmo.di.network

import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel
import com.boys.assets.jmo.data.remote.ApiService

class RepositoryImpl (private val apiService: ApiService) : Repository {

    override suspend fun getPosts(): List<PostsModel> {
        return apiService.getPosts()
    }

}