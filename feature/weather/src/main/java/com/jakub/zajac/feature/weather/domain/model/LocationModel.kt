package com.jakub.zajac.feature.weather.domain.model

data class LocationModel (
    val key: String,
    val type: String,
    val name: String,
    val countryModel: CountryModel
)