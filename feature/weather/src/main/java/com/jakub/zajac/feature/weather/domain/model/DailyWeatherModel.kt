package com.jakub.zajac.feature.weather.domain.model

data class DailyWeatherModel(
    val date: String,
    val sunDto: SunModel,
    val moonDto: SunModel,
    val temperatureDto: TemperatureModel2,
    val dayDetails: DailyDetailsModel,
    val nightDetails: DailyDetailsModel,
)

data class SunModel(
    val riseTime: String, val set: String
)

data class TemperatureModel2(
    val minimumTemperature: TemperatureValueModel, val maxTemperature: TemperatureValueModel
)

data class TemperatureValueModel(
    val value: Float, val unit: String
)

data class DailyDetailsModel(
    val icon: Int, val phrase: String, val cloudCover: Int
)