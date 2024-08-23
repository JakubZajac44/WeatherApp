package com.jakub.zajac.feature.weather.data.mapper

import com.jakub.zajac.common.network.model.WeatherHourlyDto
import com.jakub.zajac.feature.weather.domain.model.PrecipitationModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureFeelValueModel
import com.jakub.zajac.feature.weather.domain.model.TemperatureModel
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel

fun WeatherHourlyDto.toWeatherHourlyModel(): WeatherHourlyModel = WeatherHourlyModel(
    date = this.date,
    icon = this.icon,
    phrase = this.phrase,
    temperatureModel = TemperatureModel(
        value = this.temperatureDto.value, unit = this.temperatureDto.unit
    ),
    temperatureFeelModel = TemperatureFeelValueModel(
        value = this.temperatureFeelDto.value,
        unit = this.temperatureFeelDto.unit,
        phrase = this.temperatureFeelDto.phrase
    ),
    relativeHumidity = this.relativeHumidity,
    indoorRelativeHumidity = this.indoorRelativeHumidity,
    rainProbability = this.rainProbability,
    snowProbability = this.snowProbability,
    iceProbability = this.iceProbability,
    rainPrecipitationModel = PrecipitationModel(
        value = this.rainPrecipitation.value, unit = this.rainPrecipitation.unit
    ),
    snowPrecipitationModel = PrecipitationModel(
        value = this.snowPrecipitation.value, unit = this.snowPrecipitation.unit
    ),
    icePrecipitationModel = PrecipitationModel(
        value = this.icePrecipitation.value, unit = this.icePrecipitation.unit
    ),
    cloudCover = this.cloudCover
)
