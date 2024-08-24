package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureMetricDto(
    @SerialName("Metric")
    val temperatureDto: TemperatureDto,
)