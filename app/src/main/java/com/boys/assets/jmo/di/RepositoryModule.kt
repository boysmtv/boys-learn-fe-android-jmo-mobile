package com.boys.assets.jmo.di

import com.boys.assets.jmo.data.remote.ApiService
import com.boys.assets.jmo.di.network.Repository
import com.boys.assets.jmo.di.network.RepositoryImpl

fun createRepository(apiService: ApiService): Repository {
    return RepositoryImpl(apiService)
}