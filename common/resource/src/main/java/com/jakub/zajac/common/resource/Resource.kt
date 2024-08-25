package com.jakub.zajac.common.resource

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val apiException: ApiException) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean = true): Resource<Nothing>()
}