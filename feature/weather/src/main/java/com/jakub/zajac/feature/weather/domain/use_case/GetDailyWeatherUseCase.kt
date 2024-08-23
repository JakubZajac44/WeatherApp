package com.jakub.zajac.feature.weather.domain.use_case

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.data.mapper.toWeatherDailyModel
import com.jakub.zajac.feature.weather.domain.model.DailyWeatherModel
import com.jakub.zajac.feature.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDailyWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(locationKey: String): Flow<Resource<List<DailyWeatherModel>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = weatherRepository.getDailyWeather(locationKey)) {
                is ApiResult.Error -> emit(
                    Resource.Error(apiResponse.exception)
                )

                is ApiResult.Success -> emit(Resource.Success(apiResponse.data.dailyWeatherListDto.map { it.toWeatherDailyModel() }))
            }
        }
    }
}