package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto (
    @SerialName("WeatherText")
    val weatherText: String,
    @SerialName("WeatherIcon")
    val weatherIcon: Int,
    @SerialName("Temperature")
    val temperature: TemperatureMetricDto,
    @SerialName("RealFeelTemperature")
    val realFeelTemperature: TemperatureMetricDto,
    @SerialName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerialName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,
    @SerialName("CloudCover")
    val cloudCover: Int,
)