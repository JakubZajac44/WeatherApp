package com.jakub.zajac.feature.weather.presentation.weather_details

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.jakub.zajac.common.resource.SideEffect
import com.jakub.zajac.common.resource.SingleEventEffect
import com.jakub.zajac.common.resource.ui.PullToRefreshComponent
import com.jakub.zajac.feature.weather.presentation.weather_details.component.WeatherDetailsCurrentComponent
import com.jakub.zajac.feature.weather.presentation.weather_details.component.daily.WeatherDetailsDailyComponent
import com.jakub.zajac.feature.weather.presentation.weather_details.component.hourly.WeatherDetailsHourlyComponent
import kotlinx.coroutines.flow.Flow

@Composable
fun WeatherDetailsScreen(
    state: WeatherDetailsState,
    sideEffect: Flow<SideEffect>,
    event: (WeatherDetailsEvent) -> Unit,
) {

    val context = LocalContext.current
    SingleEventEffect(sideEffect) { effect ->
        when (effect) {
            is SideEffect.ShowToast -> Toast.makeText(
                context, effect.message.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            PullToRefreshComponent(
                isRefreshing = state.isRefreshing,
                onRefresh = {
                    event.invoke(WeatherDetailsEvent.RefreshWeatherData)
                },
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    WeatherDetailsCurrentComponent(
                        state.currentWeather,
                        state.locationName,
                        state.isLoadingWeatherCurrent
                    )

                    WeatherDetailsHourlyComponent(
                        weatherHourly = state.weatherHourly,
                        isLoading = state.isLoadingWeatherHourly
                    )

                    WeatherDetailsDailyComponent(
                        weatherDaily = state.weatherDaily,
                        isLoading = state.isLoadingWeatherDaily
                    )
                }
            }
        }
    }
}