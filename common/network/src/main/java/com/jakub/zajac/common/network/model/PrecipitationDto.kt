package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrecipitationDto(
    @SerialName("Value") val value: Float,
    @SerialName("Unit") val unit: String
)