package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.WeatherHourlyDto
import com.jakub.zajac.feature.weather.domain.model.TemperatureFeelValueModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel

fun WeatherHourlyDto.toWeatherHourlyModel(): WeatherHourlyModel = WeatherHourlyModel(
    date = this.date,
    icon = this.icon,
    phrase = this.phrase,
    temperature = TemperatureModel(
        value = this.temperatureDto.value, unit = this.temperatureDto.unit
    ),
    temperatureFeel = TemperatureFeelValueModel(
        value = this.temperatureFeelDto.value,
        unit = this.temperatureFeelDto.unit,
        phrase = this.temperatureFeelDto.phrase
    ),
    relativeHumidity = this.relativeHumidity,
    indoorRelativeHumidity = this.indoorRelativeHumidity,
    rainProbability = this.rainProbability,
    snowProbability = this.snowProbability,
    iceProbability = this.iceProbability,
    cloudCover = this.cloudCover
)
