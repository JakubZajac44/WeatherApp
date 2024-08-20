package com.jakub.zajac.feature.weather.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.lifecycle.SavedStateHandle
import com.jakub.zajac.feature.weather.presentation.location_search.LocationSearchNavigationEvent
import com.jakub.zajac.feature.weather.presentation.location_search.locationSearchNavigation
import com.jakub.zajac.feature.weather.presentation.weather_info.LOCATION_KEY
import com.jakub.zajac.feature.weather.presentation.weather_info.navigateToWeatherInfoScreen
import com.jakub.zajac.feature.weather.presentation.weather_info.weatherInfoNavigation

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
        route = WEATHER_GRAPH_ROUTE,
        startDestination =  Route.LocationSearchRout.route
    ){
        locationSearchNavigation( navigationEvent = { navigationEvent ->
            when(navigationEvent){
                is LocationSearchNavigationEvent.LocationSelected -> {
                    navController.navigateToWeatherInfoScreen(navigationEvent.locationKey.toString())
                }
            }
        }

        )

        weatherInfoNavigation()

    }
}