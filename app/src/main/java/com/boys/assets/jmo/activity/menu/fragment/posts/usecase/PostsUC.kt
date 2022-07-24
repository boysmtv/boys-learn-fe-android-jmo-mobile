package com.boys.assets.jmo.activity.menu.fragment.posts.usecase

import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel
import com.boys.assets.jmo.di.network.Repository
import com.boys.assets.jmo.domain.usecase.UseCase

class PostsUC constructor(
    private val repository: Repository
) : UseCase<List<PostsModel>, Any?>() {

    override suspend fun run(params: Any?): List<PostsModel> {
        return repository.getPosts()
    }

}