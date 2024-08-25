package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jakub.zajac.feature.weather.presentation.Route

internal fun NavGraphBuilder.locationSearchNavigation(
    navigationEvent: (LocationSearchNavigationEvent) -> Unit
) {
    composable(Route.LocationSearchRout.route) {

        val viewModel: LocationSearchViewModel = hiltViewModel()
        LocationSearchScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            event = viewModel::onEvent,
            navigationEvent = navigationEvent,
            sideEffect = viewModel.sideEffectFlow
        )
    }
}