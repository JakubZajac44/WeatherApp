package com.jakub.zajac.feature.weather.domain.model

data class WeatherDailyModel(
    val date: String,
    val temperatureDto: TemperatureDaily,
    val dayDetails: DailyDetailsModel,
    val nightDetails: DailyDetailsModel,
)

data class TemperatureDaily(
    val minimumTemperature: TemperatureModel, val maxTemperature: TemperatureModel
)
data class DailyDetailsModel(
    val icon: Int, val phrase: String, val cloudCover: Int
)