package com.jakub.zajac.feature.weather.presentation.weather_details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jakub.zajac.common.resource.ui.ShimmerContainer

@Composable
fun WeatherDetailsCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    isLoading: Boolean = false,
    content: @Composable () -> Unit,
) {

    ShimmerContainer(
        modifier = modifier, isLoading = isLoading
    ) {
        ElevatedCard(
            modifier = modifier
        ) {
            Box(
                modifier = modifier.background(backgroundColor)
            ) {
                content()
            }
        }
    }
}