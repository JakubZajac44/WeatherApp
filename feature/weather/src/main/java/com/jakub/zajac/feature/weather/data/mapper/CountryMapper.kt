package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.CountryDto
import com.jakub.zajac.feature.weather.domain.model.CountryModel

fun CountryDto.toCountryModel() = CountryModel(
    name = this.name
)