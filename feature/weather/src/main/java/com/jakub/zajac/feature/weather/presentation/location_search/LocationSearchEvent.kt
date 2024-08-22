package com.jakub.zajac.feature.weather.presentation.location_search

import com.jakub.zajac.feature.weather.domain.model.LocationModel

sealed class LocationSearchEvent {
    data class SearchQueryTyped(val locationQuery: String) : LocationSearchEvent()
    data object ClearSearchTyped : LocationSearchEvent()
    data class LocationSelected(val locationModel: LocationModel): LocationSearchEvent()
}

sealed class LocationSearchNavigationEvent {
    data class LocationSelected(val locationKey: String) : LocationSearchNavigationEvent()
}