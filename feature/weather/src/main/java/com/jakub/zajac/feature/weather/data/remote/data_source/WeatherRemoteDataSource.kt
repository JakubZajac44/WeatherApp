package com.jakub.zajac.feature.weather.data.remote.data_source

import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.resource.apiCall
import com.jakub.zajac.common.network.api.WeatherApi
import com.jakub.zajac.common.network.model.WeatherDailyResponse
import com.jakub.zajac.common.network.model.WeatherHourlyDto
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getHourlyWeather(locationKey: String): ApiResult<List<WeatherHourlyDto>> {
        return apiCall(call = { weatherApi.getHourlyWeather(locationKey = locationKey, details = true, metric = true) })
    }

    suspend fun getDailyWeather(locationKey: String): ApiResult<WeatherDailyResponse> {
        return apiCall(call = { weatherApi.getDailyWeather(locationKey = locationKey, details = true, metric = true) })
    }

}