package com.jakub.zajac.feature.weather.data.repository

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.storage.model.LocationEntity
import com.jakub.zajac.feature.weather.data.local.data_source.LocationLocalDataSource
import com.jakub.zajac.feature.weather.data.remote.data_source.LocationRemoteDataSource
import com.jakub.zajac.feature.weather.data.remote.model.LocationDto
import com.jakub.zajac.feature.weather.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource,
    private val locationLocalDataSource: LocationLocalDataSource
) : LocationRepository {

    override suspend fun getLocationListByQueryName(queryName: String): ApiResult<List<LocationDto>> {
        return when (val apiResult = locationRemoteDataSource.getLocationsByQueryName(queryName)) {
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> {
                ApiResult.Success(apiResult.data)
            }
        }
    }

    override suspend fun getCachedLocationList(): List<LocationEntity> {
        return locationLocalDataSource.getLocationList()
    }

    override suspend fun cacheLocation(locationEntity: LocationEntity) {
        locationLocalDataSource.insertLocation(locationEntity)
    }
}