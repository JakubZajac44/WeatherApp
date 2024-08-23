package com.jakub.zajac.feature.weather.presentation.weather_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jakub.zajac.common.resource.getWeatherIcon

@Composable
fun WeatherDetailsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text("Pogoda")


        Image(
            painterResource(getWeatherIcon(24)),
            "content description",
            modifier = Modifier
                .width(128.dp)
                .height(128.dp)
        )


    }
}