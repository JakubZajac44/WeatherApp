package com.jakub.zajac.common.resource

import android.content.res.Resources
import com.jakub.zajac.resource.R
import retrofit2.Response

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: ApiException) : ApiResult<Nothing>()
}

sealed class ApiException(override val message: String): Exception() {

    sealed class ApiBaseException(override val message: String) :
        ApiException(Resources.getSystem().getString(R.string.general_api_response_error)) {
        data object CustomApiException : ApiBaseException(Resources.getSystem().getString(R.string.server_not_responding_api_response_error)) {
            private fun readResolve(): Any = CustomApiException
        }
    }

    sealed class NetworkBaseException(override val message: String) :
        ApiException(Resources.getSystem().getString(R.string.general_api_response_error)) {
        data object NoConnectionException : NetworkBaseException(Resources.getSystem().getString(R.string.no_internet_api_response_error)) {
            private fun readResolve(): Any = NoConnectionException
        }

        data object InternalException : NetworkBaseException(Resources.getSystem().getString(R.string.general_api_response_error)) {
            private fun readResolve(): Any = InternalException
        }
    }
}

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): ApiResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return ApiResult.Error(mapNetworkThrowable(t))
    }

    return if (!response.isSuccessful) {
        ApiResult.Error(ApiException.ApiBaseException.CustomApiException)
    } else {
        response.body()?.let {
            ApiResult.Success(it)
        } ?: run {
            ApiResult.Error(ApiException.ApiBaseException.CustomApiException)
        }
    }
}

private fun mapNetworkThrowable(throwable: Throwable): ApiException {
    return when (throwable) {
        is java.net.UnknownHostException -> ApiException.NetworkBaseException.NoConnectionException
        is java.net.SocketTimeoutException -> ApiException.NetworkBaseException.NoConnectionException
        else -> ApiException.NetworkBaseException.InternalException
    }
}