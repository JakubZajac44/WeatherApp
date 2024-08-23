package com.jakub.zajac.feature.weather.presentation

sealed class Route(val route: String) {
    data object LocationSearchRout : Route("location-search-rout")
    data object WeatherDetailsRout : Route("weather_info_route/{locationKey}")
}