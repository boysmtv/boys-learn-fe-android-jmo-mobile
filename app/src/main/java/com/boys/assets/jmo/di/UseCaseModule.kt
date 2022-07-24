package com.boys.assets.jmo.di

import com.boys.assets.jmo.activity.menu.fragment.posts.usecase.PostsUC
import com.boys.assets.jmo.di.network.Repository

fun getPostsUC(repository: Repository): PostsUC {
    return PostsUC(repository)
}