package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationSearchScreen(
    navigationEvent: (LocationSearchNavigationEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text("Wyszukiwarka")

        Button(onClick = { navigationEvent.invoke(LocationSearchNavigationEvent.LocationSelected(5)) }) {
            Text("Ide dalej")
        }

    }
}