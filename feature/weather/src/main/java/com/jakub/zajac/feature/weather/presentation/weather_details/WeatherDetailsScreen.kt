package com.jakub.zajac.feature.weather.presentation.weather_details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jakub.zajac.common.resource.SideEffect
import com.jakub.zajac.common.resource.SingleEventEffect
import com.jakub.zajac.common.resource.getWeatherIcon
import kotlinx.coroutines.flow.Flow

@Composable
fun WeatherDetailsScreen(
    modifier: Modifier = Modifier,
    state: WeatherDetailsState,
    sideEffect: Flow<SideEffect>,
    event: (WeatherDetailsEvent) -> Unit,
) {

    val context = LocalContext.current
    SingleEventEffect(sideEffect) { effect ->
        when (effect) {
            is SideEffect.ShowToast -> Toast.makeText(
                context,
                effect.message.asString(context),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = modifier
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