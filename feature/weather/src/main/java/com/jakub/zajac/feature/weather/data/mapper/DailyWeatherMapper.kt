package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.WeatherDailyDto
import com.jakub.zajac.feature.weather.domain.model.DailyDetailsModel
import com.jakub.zajac.feature.weather.domain.model.SunModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureDaily
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel
import com.jakub.zajac.feature.weather.domain.model.WeatherDailyModel

fun WeatherDailyDto.toWeatherDailyModel(): WeatherDailyModel = WeatherDailyModel(
    date = this.date,
    sunDto = SunModel(riseTime = this.sunDto.riseTime, set = this.sunDto.set),
    moonDto = SunModel(riseTime = this.moonDto.riseTime, set = this.moonDto.set),
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