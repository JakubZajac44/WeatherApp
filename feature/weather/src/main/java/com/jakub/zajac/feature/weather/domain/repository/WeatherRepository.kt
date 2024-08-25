package com.jakub.zajac.feature.weather.domain.repository

import com.jakub.zajac.common.network.model.CurrentWeatherDto
import com.jakub.zajac.common.resource.ApiResult
import com.jakub.zajac.common.network.model.response.WeatherDailyResponse
import com.jakub.zajac.common.network.model.WeatherHourlyDto

interface WeatherRepository {

    suspend fun getHourlyWeather(locationKey: String): ApiResult<List<WeatherHourlyDto>>

    suspend fun getDailyWeather(locationKey: String): ApiResult<WeatherDailyResponse>

    suspend fun getCurrentWeather(locationKey: String): ApiResult<List<CurrentWeatherDto>>

}