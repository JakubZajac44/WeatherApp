package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDailyDto(
    @SerialName("Date") val date: String,
    @SerialName("Sun") val sunDto: SunDto,
    @SerialName("Moon") val moonDto: SunDto,
    @SerialName("Temperature") val temperatureDto: DailyTemperatureDto,
    @SerialName("Day") val dayDetails: DailyDetailsDto,
    @SerialName("Night") val nightDetails: DailyDetailsDto,
    )