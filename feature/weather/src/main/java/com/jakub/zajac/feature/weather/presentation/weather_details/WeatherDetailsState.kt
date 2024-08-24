package com.jakub.zajac.feature.weather.presentation.weather_details

import com.jakub.zajac.feature.weather.domain.model.CurrentWeatherModel
import com.jakub.zajac.feature.weather.domain.model.WeatherDailyModel
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel

data class WeatherDetailsState(
    val currentWeather: CurrentWeatherModel? = null,
    val weatherHourly: List<WeatherHourlyModel> = listOf(),
    val weatherDailyModel: List<WeatherDailyModel> = listOf(),
    val isLoadingWeatherHourly: Boolean = false,
    val isLoadingWeatherDaily: Boolean = false,
    val isLoadingWeatherCurrent: Boolean = false
)