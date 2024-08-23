package com.jakub.zajac.feature.weather.data.remote.data_source

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.apiCall
import com.jakub.zajac.common.network.api.LocationApi
import com.jakub.zajac.common.network.model.LocationDto
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationApi: LocationApi
){
    suspend fun getLocationsByQueryName(queryName: String): ApiResult<List<LocationDto>> {
        return apiCall(call = {locationApi.getLocationsByName(queryName)})
    }

}