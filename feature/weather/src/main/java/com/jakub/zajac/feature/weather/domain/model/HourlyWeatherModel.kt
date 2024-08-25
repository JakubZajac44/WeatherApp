package com.jakub.zajac.feature.weather.domain.model

data class WeatherHourlyModel(
    val date: String,
    val icon: Int,
    val phrase: String,
    val temperature: TemperatureModel,
    val temperatureFeel: TemperatureFeelValueModel,
    val relativeHumidity: Int,
    val indoorRelativeHumidity: Int,
    val rainProbability: Int,
    val snowProbability: Int,
    val iceProbability: Int,
    val cloudCover: Int,
)