package com.jakub.zajac.feature.weather.presentation.location_search

import com.jakub.zajac.feature.weather.domain.model.LocationModel

data class LocationSearchState(
    val locationList: List<LocationModel> = listOf(),
    val cachedLocationList: List<LocationModel> = listOf(),
    val locationNotFound: Boolean = false,
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val inputErrorMessage: String = ""
)