package com.jakub.zajac.feature.weather.domain.repository

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.feature.weather.data.remote.model.LocationDto

interface LocationRepository {

    suspend fun getLocationListByQueryName(queryName: String): ApiResult<List<LocationDto>>

}