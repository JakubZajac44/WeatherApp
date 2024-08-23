package com.jakub.zajac.feature.weather.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.jakub.zajac.feature.weather.presentation.location_search.LocationSearchNavigationEvent
import com.jakub.zajac.feature.weather.presentation.location_search.locationSearchNavigation
import com.jakub.zajac.feature.weather.presentation.weather_details.LOCATION_KEY
import com.jakub.zajac.feature.weather.presentation.weather_details.navigateToWeatherDetailsScreen
import com.jakub.zajac.feature.weather.presentation.weather_details.weatherDetailsNavigation

const val WEATHER_GRAPH_ROUTE = "weather-graph"

internal data class LocationKeyArg(val locationKey: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        locationKey = checkNotNull(savedStateHandle[LOCATION_KEY])
    )
}

fun NavGraphBuilder.weatherGraph(
    navController: NavController,
) {

    navigation(
        route = WEATHER_GRAPH_ROUTE, startDestination = Route.LocationSearchRout.route
    ) {
        locationSearchNavigation(navigationEvent = { navigationEvent ->
            when (navigationEvent) {
                is LocationSearchNavigationEvent.LocationSelected -> {
                    navController.navigateToWeatherDetailsScreen(navigationEvent.locationKey)
                }
            }
        })

        weatherDetailsNavigation()

    }
}