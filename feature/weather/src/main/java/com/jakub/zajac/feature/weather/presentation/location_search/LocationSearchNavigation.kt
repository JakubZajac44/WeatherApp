package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jakub.zajac.feature.weather.presentation.Route


internal fun NavGraphBuilder.locationSearchNavigation(
    navigationEvent: (LocationSearchNavigationEvent) -> Unit
) {
    composable(Route.LocationSearchRout.route) {
        LocationSearchScreen(
            navigationEvent = navigationEvent
        )
    }
}