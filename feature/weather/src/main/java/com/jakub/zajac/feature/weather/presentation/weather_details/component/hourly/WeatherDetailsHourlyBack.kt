package com.jakub.zajac.feature.weather.presentation.weather_details.component.hourly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel

@Composable
fun WeatherDetailsHourlyBack(weatherHourly: WeatherHourlyModel) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(2.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(R.string.real_temperature, weatherHourly.temperatureFeel.value, weatherHourly.temperatureFeel.unit))
        Text(
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(R.string.humidity, weatherHourly.relativeHumidity))
        Text(
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(R.string.probability_title))
        Text(
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(
            R.string.rain_probability,
            weatherHourly.rainProbability
        )
        )
        Text(
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(
            R.string.snow_probability,
            weatherHourly.snowProbability
        )
        )
        Text(
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            maxLines = 2,
            text = stringResource(
            R.string.ice_probability,
            weatherHourly.iceProbability
        )
        )
    }
}