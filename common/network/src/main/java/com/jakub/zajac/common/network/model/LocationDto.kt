package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("Key") val key: String,
    @SerialName("Type") val type: String,
    @SerialName("LocalizedName") val name: String,
    @SerialName("Country") val countryDto: CountryDto
)