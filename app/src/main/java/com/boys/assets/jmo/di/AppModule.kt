package com.boys.assets.jmo.di

import com.boys.assets.jmo.activity.login.vm.LoginViewModel
import com.boys.assets.jmo.activity.menu.fragment.posts.presentation.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStag = module {
    viewModel { LoginViewModel() }
    viewModel { PostsViewModel(get()) }

    single { getPostsUC(get()) }
    single { createRepository(get()) }
}