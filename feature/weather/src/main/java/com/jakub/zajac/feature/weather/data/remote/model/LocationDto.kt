package com.jakub.zajac.feature.weather.data.remote.model

import kotlinx.serialization.SerialName

data class LocationDto (
    @SerialName("Key")
    val key: String,
    @SerialName("Type")
    val type: String,
    @SerialName("LocalizedName")
    val name: String,
    @SerialName("Country")
    val countryDto: CountryDto

)

data class CountryDto(
    @SerialName("ID")
    val id: String,
    @SerialName("LocalizedName")
    val name: String
)