package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.CurrentWeatherDto
import com.jakub.zajac.feature.weather.domain.model.CurrentWeatherModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel

fun CurrentWeatherDto.toCurrentWeatherModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
        weatherText = this.weatherText,
        weatherIcon = this.weatherIcon,
        temperature = TemperatureModel(
            value = this.temperature.temperatureDto.value,
            unit = this.temperature.temperatureDto.unit
        ),
        realFeelTemperature = TemperatureModel(
            value = this.realFeelTemperature.temperatureDto.value,
            unit = this.realFeelTemperature.temperatureDto.unit
        ),
        relativeHumidity = this.relativeHumidity,
        indoorRelativeHumidity = this.indoorRelativeHumidity,
        cloudCover = this.cloudCover,
    )
}