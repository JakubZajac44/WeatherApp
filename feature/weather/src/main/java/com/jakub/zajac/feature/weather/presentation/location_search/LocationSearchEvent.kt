package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.ui.text.input.TextFieldValue

sealed class LocationSearchEvent {
    data class SearchQueryTyped(val locationQuery: TextFieldValue) : LocationSearchEvent()
    data object ClearSearchTyped : LocationSearchEvent()
}

sealed class LocationSearchNavigationEvent {
    data class LocationSelected(val locationKey: String) : LocationSearchNavigationEvent()
}