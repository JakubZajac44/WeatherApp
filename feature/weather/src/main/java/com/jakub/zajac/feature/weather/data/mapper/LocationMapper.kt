package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.storage.model.LocationEntity
import com.jakub.zajac.feature.weather.data.remote.model.LocationDto
import com.jakub.zajac.feature.weather.domain.model.CountryModel
import com.jakub.zajac.feature.weather.domain.model.LocationModel

fun LocationDto.toLocationModel() = LocationModel(
    key = this.key,
    type = this.type,
    name = this.name,
    countryModel = this.countryDto.toCountryModel()
)

fun LocationEntity.toLocationModel() = LocationModel(
    key = this.key,
    type = this.type,
    name = this.name,
    countryModel = CountryModel(this.name)
)

fun LocationModel.toLocationEntity() = LocationEntity(
    key = this.key,
    type = this.type,
    name = this.name,
    country = this.countryModel.name,
    usageNumber = 0
)