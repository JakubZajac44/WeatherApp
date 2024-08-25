package com.jakub.zajac.common.network.model.response

import com.jakub.zajac.common.network.model.WeatherDailyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDailyResponse(
    @SerialName("DailyForecasts") val dailyWeatherListDto: List<WeatherDailyDto>
)