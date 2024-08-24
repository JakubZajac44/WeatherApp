package com.jakub.zajac.common.resource

import com.jakub.zajac.resource.R

fun handleApiError(apiError: ApiException): UiText{
    return when(apiError){
        ApiException.GeneralException -> UiText.StringResource(R.string.general_api_response_error)
        ApiException.NoInternet -> UiText.StringResource(R.string.no_internet_api_response_error)
        ApiException.ServerNotResponding -> UiText.StringResource(R.string.server_not_responding_api_response_error)
    }
}