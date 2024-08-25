package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SunDto(
    @SerialName("Rise") val riseTime: String, @SerialName("Set") val set: String
)