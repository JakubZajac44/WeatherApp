package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyTemperatureDto(
    @SerialName("Minimum") val minimumTemperature: TemperatureValueDto,
    @SerialName("Maximum") val maxTemperature: TemperatureValueDto
)