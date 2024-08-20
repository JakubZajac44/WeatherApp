package com.jakub.zajac.feature.weather.presentation.location_search

sealed class LocationSearchEvent {
}

sealed class LocationSearchNavigationEvent {
    data class LocationSelected(val locationKey: Int): LocationSearchNavigationEvent()
}