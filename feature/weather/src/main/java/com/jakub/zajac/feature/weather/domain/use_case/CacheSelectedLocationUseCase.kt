package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.feature.weather.data.mapper.toLocationEntity
import com.jakub.zajac.feature.weather.domain.model.LocationModel
import com.jakub.zajac.feature.weather.domain.repository.LocationRepository
import javax.inject.Inject

class CacheSelectedLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(locationModel: LocationModel) {

        val cachedLocationList = locationRepository.getCachedLocationList()

        val locationEntity = cachedLocationList.find { it.key == locationModel.key }?.let{ cachedLocation ->
            locationModel.toLocationEntity(cachedLocation.usageNumber + 1)
        } ?: run {
            locationModel.toLocationEntity(1)
        }
        locationRepository.cacheLocation(locationEntity)
    }
}