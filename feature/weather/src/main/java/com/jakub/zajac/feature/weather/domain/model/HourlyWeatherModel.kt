package com.jakub.zajac.feature.weather.domain.model


data class WeatherHourlyModel(
    val date: String,
    val icon: Int,
    val phrase: String,
    val temperatureModel: TemperatureModel,
    val temperatureFeelModel: TemperatureFeelValueModel,
    val relativeHumidity: Int,
    val indoorRelativeHumidity: Int,
    val rainProbability: Int,
    val snowProbability: Int,
    val iceProbability: Int,
    val rainPrecipitationModel: PrecipitationModel,
    val snowPrecipitationModel: PrecipitationModel,
    val icePrecipitationModel: PrecipitationModel,
    val cloudCover: Int,
)

data class TemperatureModel(
    val value: Float, val unit: String
)

data class TemperatureFeelValueModel(
    val value: Float, val unit: String, val phrase: String
)

data class PrecipitationModel(
    val value: Float, val unit: String
)