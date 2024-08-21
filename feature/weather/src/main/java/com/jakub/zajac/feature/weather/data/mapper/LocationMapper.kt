package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.feature.weather.data.remote.model.LocationDto
import com.jakub.zajac.feature.weather.domain.model.LocationModel

fun LocationDto.toLocationModel() = LocationModel(
    key = this.key,
    type = this.type,
    name = this.name,
    countryModel = this.countryDto.toCountryModel()
)