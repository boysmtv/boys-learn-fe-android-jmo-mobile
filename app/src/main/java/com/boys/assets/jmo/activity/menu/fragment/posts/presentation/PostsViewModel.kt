package com.boys.assets.jmo.activity.menu.fragment.posts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel
import com.boys.assets.jmo.activity.menu.fragment.posts.usecase.PostsUC
import com.boys.assets.jmo.domain.model.ApiError
import com.boys.assets.jmo.domain.usecase.UseCaseResponse
import kotlinx.coroutines.cancel

class PostsViewModel constructor(private val postsUC: PostsUC) : ViewModel() {

    val onSuccess = MutableLiveData<List<PostsModel>>()
    val onProgress = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String>()

    fun getFlash() {
        onProgress.value = true
        postsUC.invoke(viewModelScope, null, object : UseCaseResponse<List<PostsModel>> {
            override fun onSuccess(result: List<PostsModel>) {
                onSuccess.value = result
                onProgress.value = false
            }

            override fun onError(apiError: ApiError?) {
                onError.value = apiError?.getErrorMessage()
                onProgress.value = false
            }
        },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}