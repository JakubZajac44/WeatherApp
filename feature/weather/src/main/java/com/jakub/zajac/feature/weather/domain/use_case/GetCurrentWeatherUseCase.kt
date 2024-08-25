package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.common.resource.ApiException
import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.data.mapper.toCurrentWeatherModel
import com.jakub.zajac.feature.weather.domain.model.CurrentWeatherModel
import com.jakub.zajac.feature.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(locationKey: String): Flow<Resource<CurrentWeatherModel>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = weatherRepository.getCurrentWeather(locationKey)) {
                is ApiResult.Error -> emit(
                    Resource.Error(apiResponse.exception)
                )

                is ApiResult.Success -> {
                    apiResponse.data.firstOrNull()?.let { currentWeather ->
                        emit(Resource.Success(currentWeather.toCurrentWeatherModel()))
                    } ?: run {
                        Resource.Error(ApiException.GeneralException)
                    }
                }
            }
        }
    }
}