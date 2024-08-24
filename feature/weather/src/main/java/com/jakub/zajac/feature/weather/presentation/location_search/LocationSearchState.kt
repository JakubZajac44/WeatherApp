package com.jakub.zajac.feature.weather.presentation.location_search

import com.jakub.zajac.feature.weather.domain.model.LocationModel
import com.jakub.zajac.common.resource.UiText

data class LocationSearchState(
    val locationList: List<LocationModel> = listOf(),
    val cachedLocationList: List<LocationModel> = listOf(),
    val locationNotFound: Boolean = false,
    val isLoading: Boolean = false,
    val inputErrorMessage: ValidationResult = ValidationResult(),
)

data class ValidationResult(
    val isQueryValid: Boolean = true, val errorMessage: UiText = UiText.DynamicString("")
)
