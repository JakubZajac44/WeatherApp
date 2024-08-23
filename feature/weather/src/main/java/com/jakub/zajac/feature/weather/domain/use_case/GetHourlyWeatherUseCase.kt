package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.data.mapper.toWeatherHourlyModel
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel
import com.jakub.zajac.feature.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHourlyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(locationKey: String): Flow<Resource<List<WeatherHourlyModel>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = weatherRepository.getHourlyWeather(locationKey)) {
                is ApiResult.Error -> emit(
                    Resource.Error(apiResponse.exception)
                )

                is ApiResult.Success -> emit(Resource.Success(apiResponse.data.map { it.toWeatherHourlyModel() }))
            }
        }
    }
}