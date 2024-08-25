package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("ID")
    val id: String,
    @SerialName("LocalizedName")
    val name: String
)