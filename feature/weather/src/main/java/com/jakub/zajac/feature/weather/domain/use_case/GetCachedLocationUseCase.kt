package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.data.mapper.toLocationModel
import com.jakub.zajac.feature.weather.domain.model.LocationModel
import com.jakub.zajac.feature.weather.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<LocationModel>>> {
        return flow {
            emit(Resource.Loading())
            val cachedLocationList = locationRepository.getCachedLocationList()
            emit(Resource.Success(cachedLocationList.sortedByDescending { it.usageNumber }.map { it.toLocationModel() }))
        }
    }
}