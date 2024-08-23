package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureFeelValueDto(
    @SerialName("Value") val value: Float,
    @SerialName("Unit") val unit: String,
    @SerialName("Phrase") val phrase: String
)