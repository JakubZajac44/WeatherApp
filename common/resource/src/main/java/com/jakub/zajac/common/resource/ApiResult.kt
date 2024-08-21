package com.jakub.zajac.common.resource

import retrofit2.Response

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: ApiException) : ApiResult<Nothing>()
}

sealed class ApiException {
    data object GeneralException : ApiException()
    data object ServerNotResponding : ApiException()
    data object NoInternet : ApiException()
}

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): ApiResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return ApiResult.Error(mapNetworkThrowable(t))
    }

    return if (!response.isSuccessful) {
        ApiResult.Error(ApiException.GeneralException)
    } else {
        response.body()?.let {
            ApiResult.Success(it)
        } ?: run {
            ApiResult.Error(ApiException.GeneralException)
        }
    }
}

private fun mapNetworkThrowable(throwable: Throwable): ApiException {
    return when (throwable) {
        is java.net.UnknownHostException -> ApiException.ServerNotResponding
        is java.net.SocketTimeoutException -> ApiException.ServerNotResponding
        else -> ApiException.NoInternet
    }
}