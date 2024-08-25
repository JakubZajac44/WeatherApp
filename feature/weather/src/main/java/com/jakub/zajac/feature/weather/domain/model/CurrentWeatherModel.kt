package com.jakub.zajac.feature.weather.domain.model

data class CurrentWeatherModel(
    val weatherText: String,
    val weatherIcon: Int,
    val temperature: TemperatureModel,
    val realFeelTemperature: TemperatureModel,
    val relativeHumidity: Int,
    val indoorRelativeHumidity: Int,
    val cloudCover: Int,
)