package com.jakub.zajac.feature.weather.presentation.weather_details.component.daily

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.common.resource.getWeatherIcon
import com.jakub.zajac.common.resource.util.parseFullDateToMonthAndDay
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.model.WeatherDailyModel

@Composable
fun WeatherDetailsDailyCard(weatherDailyModel: WeatherDailyModel) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(2.dp), verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherDailyModel.date.parseFullDateToMonthAndDay(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(getWeatherIcon(weatherDailyModel.dayDetails.icon)),
                    stringResource(R.string.weather_icon_db),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .width(64.dp)
                        .height(64.dp)

                )

                Image(
                    painterResource(getWeatherIcon(weatherDailyModel.nightDetails.icon)),
                    stringResource(R.string.weather_icon_db),
                    modifier = Modifier
                        .fillMaxWidth(1.0f)
                        .width(64.dp)
                        .height(64.dp)

                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = weatherDailyModel.dayDetails.phrase,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}