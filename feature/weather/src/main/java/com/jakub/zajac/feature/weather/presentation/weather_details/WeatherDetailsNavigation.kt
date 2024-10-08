package com.jakub.zajac.feature.weather.presentation.weather_details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jakub.zajac.feature.weather.presentation.Route

const val LOCATION_KEY = "locationKey"
const val LOCATION_NAME = "locationName"

internal fun NavController.navigateToWeatherDetailsScreen(
    locationKey: String,
    locationName: String
) {
    navigate(
        Route.WeatherDetailsRout.route.replace("{$LOCATION_KEY}", locationKey).replace("{$LOCATION_NAME}", locationName)
    )
}

internal fun NavGraphBuilder.weatherDetailsNavigation(
) {
    composable( Route.WeatherDetailsRout.route,
        arguments = listOf(navArgument(LOCATION_KEY){
            type = NavType.StringType
        })
    ) {
        val viewModel: WeatherDetailsViewModel = hiltViewModel()
        WeatherDetailsScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            sideEffect = viewModel.sideEffectFlow,
            event = viewModel::onEvent
        )
    }
}