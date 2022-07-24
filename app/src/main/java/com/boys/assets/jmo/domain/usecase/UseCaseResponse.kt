package com.boys.assets.jmo.domain.usecase

import com.boys.assets.jmo.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)

}

