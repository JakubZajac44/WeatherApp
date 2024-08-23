package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.WeatherDailyDto
import com.jakub.zajac.feature.weather.domain.model.DailyDetailsModel
import com.jakub.zajac.feature.weather.domain.model.DailyWeatherModel
import com.jakub.zajac.feature.weather.domain.model.SunModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel2
import com.jakub.zajac.feature.weather.domain.model.TemperatureValueModel

fun WeatherDailyDto.toWeatherDailyModel(): DailyWeatherModel = DailyWeatherModel(
    date = this.date,
    sunDto = SunModel(riseTime = this.sunDto.riseTime, set = this.sunDto.set),
    moonDto = SunModel(riseTime = this.moonDto.riseTime, set = this.moonDto.set),
    temperatureDto = TemperatureModel2(
        minimumTemperature = TemperatureValueModel(
            value = this.temperatureDto.minimumTemperature.value,
            unit = this.temperatureDto.minimumTemperature.unit
        ), maxTemperature = TemperatureValueModel(
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