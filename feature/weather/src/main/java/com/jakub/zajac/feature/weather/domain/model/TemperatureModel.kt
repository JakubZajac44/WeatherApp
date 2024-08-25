package com.jakub.zajac.feature.weather.domain.model

data class TemperatureModel(
    val value: Float, val unit: String
)

data class TemperatureFeelValueModel(
    val value: Float, val unit: String, val phrase: String
)