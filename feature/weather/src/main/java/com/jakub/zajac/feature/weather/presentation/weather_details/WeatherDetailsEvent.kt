package com.jakub.zajac.feature.weather.presentation.weather_details

sealed class WeatherDetailsEvent {
    data object RefreshWeatherData : WeatherDetailsEvent()
}