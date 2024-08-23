package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDailyResponse(
    @SerialName("DailyForecasts") val dailyWeatherListDto: List<WeatherDailyDto>
)