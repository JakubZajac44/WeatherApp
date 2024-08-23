package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDetailsDto(
    @SerialName("Icon") val icon: Int,
    @SerialName("ShortPhrase") val phrase: String,
    @SerialName("CloudCover") val cloudCover: Int
)