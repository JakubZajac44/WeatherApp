package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.data.mapper.toLocationModel
import com.jakub.zajac.feature.weather.domain.model.LocationModel
import com.jakub.zajac.feature.weather.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(
        nameQuery: String
    ): Flow<Resource<List<LocationModel>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = locationRepository.getLocationListByQueryName(nameQuery)) {
                is ApiResult.Error -> emit(
                    Resource.Error(apiResponse.exception)
                )
                is ApiResult.Success ->
                    emit(Resource.Success(apiResponse.data.map { it.toLocationModel() }))
            }
        }
    }
}