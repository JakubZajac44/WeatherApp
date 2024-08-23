package com.jakub.zajac.feature.weather.domain.repository

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.storage.model.LocationEntity
import com.jakub.zajac.common.network.model.LocationDto

interface LocationRepository {

    suspend fun getLocationListByQueryName(queryName: String): ApiResult<List<LocationDto>>

    suspend fun getCachedLocationList(): List<LocationEntity>

    suspend fun cacheLocation(locationEntity: LocationEntity)

}