package com.jakub.zajac.feature.weather.presentation.weather_details.component.hourly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jakub.zajac.common.resource.ui.FlipCardComponent
import com.jakub.zajac.common.resource.ui.RotationDirection
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.model.WeatherHourlyModel
import com.jakub.zajac.feature.weather.presentation.weather_details.component.WeatherDetailsCard

@Composable
fun WeatherDetailsHourlyComponent(
    weatherHourly: List<WeatherHourlyModel>, isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(250.dp)
    ) {
        WeatherDetailsCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp)),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            isLoading = isLoading
        ) {
            if (weatherHourly.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.error_loading_data))
                }
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(vertical = 15.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.weather_for_next_hours),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        items(weatherHourly) { weatherHourly ->

                            FlipCardComponent(modifier = Modifier
                                .width(120.dp)
                                .fillMaxHeight(),
                                rotationDirection = RotationDirection.RotationY,
                                front = {
                                    WeatherDetailsHourlyFront(weatherHourly)
                                },
                                back = {
                                    WeatherDetailsHourlyBack(weatherHourly)
                                })
                        }
                    }
                }
            }
        }
    }
}