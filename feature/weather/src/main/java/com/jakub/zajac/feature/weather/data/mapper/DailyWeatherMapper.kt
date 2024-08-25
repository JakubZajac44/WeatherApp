package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.WeatherDailyDto
import com.jakub.zajac.feature.weather.domain.model.DailyDetailsModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureDaily
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel
import com.jakub.zajac.feature.weather.domain.model.WeatherDailyModel

fun WeatherDailyDto.toWeatherDailyModel(): WeatherDailyModel = WeatherDailyModel(
    date = this.date,
    temperatureDto = TemperatureDaily(
        minimumTemperature = TemperatureModel(
            value = this.temperatureDto.minimumTemperature.value,
            unit = this.temperatureDto.minimumTemperature.unit
        ), maxTemperature = TemperatureModel(
            value = this.temperatureDto.maxTemperature.value,
            unit = this.temperatureDto.maxTemperature.unit,
        )
    ),
    dayDetails = DailyDetailsModel(
        icon = this.dayDetails.icon,
        phrase = this.dayDetails.phrase,
        cloudCover = this.dayDetails.cloudCover
    ),
    nightDetails = DailyDetailsModel(
        icon = this.nightDetails.icon,
        phrase = this.nightDetails.phrase,
        cloudCover = this.nightDetails.cloudCover
    ),
)