package com.jakub.zajac.feature.weather.presentation.location_search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.model.LocationModel

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    model: LocationModel,
    backgroundColor: Color = Color.White,
    onItemClick: (model: LocationModel) -> Unit,
) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(model) },
        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painterResource(com.jakub.zajac.resource.R.drawable.ic_city_icon),
                stringResource(R.string.image_city_icon_cd),
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start

            ) {
                val dividerColor = MaterialTheme.colorScheme.inversePrimary
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start

                ) {

                    Text(text = stringResource(R.string.city),
                        fontSize = 10.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()

                                drawLine(
                                    color = dividerColor,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            })

                    Text(
                        text = model.name,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = stringResource(R.string.country),
                        fontSize = 8.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()

                                drawLine(
                                    color = dividerColor,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            })
                    Text(
                        text = model.countryModel.name,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}