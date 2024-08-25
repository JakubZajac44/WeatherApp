package com.jakub.zajac.weatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jakub.zajac.feature.weather.presentation.WEATHER_GRAPH_ROUTE
import com.jakub.zajac.feature.weather.presentation.weatherGraph

@Composable
fun RootNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = WEATHER_GRAPH_ROUTE
    ) {
        weatherGraph(
            navController = navController
        )
    }
}