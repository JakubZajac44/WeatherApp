package com.jakub.zajac.feature.weather.data.repository

import com.jakub.zajac.common.network.model.CurrentWeatherDto
import com.jakub.zajac.common.network.model.WeatherHourlyDto
import com.jakub.zajac.common.network.model.response.WeatherDailyResponse
import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.feature.weather.data.data_source.remote.WeatherRemoteDataSource
import com.jakub.zajac.feature.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getHourlyWeather(locationKey: String): ApiResult<List<WeatherHourlyDto>> {
        return when (val apiResult = weatherRemoteDataSource.getHourlyWeather(locationKey)) {
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> {
                ApiResult.Success(apiResult.data)
            }
        }
    }

    override suspend fun getDailyWeather(locationKey: String): ApiResult<WeatherDailyResponse> {
        return when (val apiResult = weatherRemoteDataSource.getDailyWeather(locationKey)) {
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> {
                ApiResult.Success(apiResult.data)
            }
        }
    }

    override suspend fun getCurrentWeather(locationKey: String): ApiResult<List<CurrentWeatherDto>> {
        return when (val apiResult = weatherRemoteDataSource.getCurrentWeather(locationKey)) {
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> ApiResult.Success(apiResult.data)
        }
    }
}