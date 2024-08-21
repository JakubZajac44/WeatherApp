package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.ui.text.input.TextFieldValue
import com.jakub.zajac.feature.weather.domain.model.LocationModel

data class LocationSearchState(
    val locationList: List<LocationModel> = listOf(),
    var searchQuery: TextFieldValue = TextFieldValue(""),
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val errorMessage: String = ""
)