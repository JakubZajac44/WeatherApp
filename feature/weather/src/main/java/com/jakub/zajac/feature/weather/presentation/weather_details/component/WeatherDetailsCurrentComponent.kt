package com.jakub.zajac.feature.weather.presentation.weather_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.common.resource.getWeatherIcon
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.model.CurrentWeatherModel

@Composable
fun WeatherDetailsCurrentComponent(
    currentWeather: CurrentWeatherModel?, locationName: String, isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(330.dp)
    ) {
        WeatherDetailsCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp)),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            isLoading = isLoading
        ) {
            currentWeather?.let { weather ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = locationName,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                            Text(
                                text = "${weather.temperature.value} ${weather.temperature.unit}",
                                fontSize = 36.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = weather.weatherText,
                                fontSize = 20.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth(1.0f),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painterResource(getWeatherIcon(currentWeather.weatherIcon)),
                                stringResource(R.string.weather_icon_db),
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(128.dp)
                            )
                        }
                    }
                    val dividerColor = MaterialTheme.colorScheme.inversePrimary
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawBehind {
                                            val strokeWidthPx = 1.dp.toPx()
                                            val verticalOffset = size.height - 2.sp.toPx()

                                            drawLine(
                                                color = dividerColor,
                                                strokeWidth = strokeWidthPx,
                                                start = Offset(0f, verticalOffset),
                                                end = Offset(
                                                    size.width, verticalOffset
                                                )
                                            )
                                        },
                                    fontSize = 10.sp,
                                    text = stringResource(R.string.real_temperature_title),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    "${weather.realFeelTemperature.value} ${weather.realFeelTemperature.unit}"
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.fillMaxWidth(1f)) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawBehind {
                                            val strokeWidthPx = 1.dp.toPx()
                                            val verticalOffset = size.height - 2.sp.toPx()

                                            drawLine(
                                                color = dividerColor,
                                                strokeWidth = strokeWidthPx,
                                                start = Offset(0f, verticalOffset),
                                                end = Offset(
                                                    size.width, verticalOffset
                                                )
                                            )
                                        },
                                    fontSize = 10.sp,
                                    text = stringResource(R.string.cloud_cover_title),
                                    textAlign = TextAlign.Start
                                )
                                Text(stringResource(R.string.cloud_cover_value, weather.cloudCover))
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawBehind {
                                            val strokeWidthPx = 1.dp.toPx()
                                            val verticalOffset = size.height - 2.sp.toPx()

                                            drawLine(
                                                color = dividerColor,
                                                strokeWidth = strokeWidthPx,
                                                start = Offset(0f, verticalOffset),
                                                end = Offset(
                                                    size.width, verticalOffset
                                                )
                                            )
                                        },
                                    fontSize = 10.sp,
                                    text = stringResource(R.string.humidity_title),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    stringResource(
                                        R.string.humidity_value, weather.indoorRelativeHumidity
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.fillMaxWidth(1.0f)) {
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .drawBehind {
                                                val strokeWidthPx = 1.dp.toPx()
                                                val verticalOffset = size.height - 2.sp.toPx()

                                                drawLine(
                                                    color = dividerColor,
                                                    strokeWidth = strokeWidthPx,
                                                    start = Offset(0f, verticalOffset),
                                                    end = Offset(
                                                        size.width, verticalOffset
                                                    )
                                                )
                                            },
                                        fontSize = 10.sp,
                                        text = stringResource(R.string.real_humidity_title),
                                        textAlign = TextAlign.Start
                                    )
                                    Text(
                                        stringResource(
                                            R.string.real_humidity_value, weather.relativeHumidity
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.error_loading_data))
                }
            }
        }
    }
}