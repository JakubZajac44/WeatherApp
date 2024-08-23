package com.jakub.zajac.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherHourlyDto(
    @SerialName("DateTime") val date: String,
    @SerialName("WeatherIcon") val icon: Int,
    @SerialName("IconPhrase") val phrase: String,
    @SerialName("Temperature") val temperatureDto: TemperatureDto,
    @SerialName("RealFeelTemperature") val temperatureFeelDto: TemperatureFeelValueDto,
    @SerialName("RelativeHumidity") val relativeHumidity: Int,
    @SerialName("IndoorRelativeHumidity") val indoorRelativeHumidity: Int,
    @SerialName("RainProbability") val rainProbability: Int,
    @SerialName("SnowProbability") val snowProbability: Int,
    @SerialName("IceProbability") val iceProbability: Int,
    @SerialName("Rain") val rainPrecipitation: PrecipitationDto,
    @SerialName("Snow") val snowPrecipitation: PrecipitationDto,
    @SerialName("Ice") val icePrecipitation: PrecipitationDto,
    @SerialName("CloudCover") val cloudCover: Int,
)