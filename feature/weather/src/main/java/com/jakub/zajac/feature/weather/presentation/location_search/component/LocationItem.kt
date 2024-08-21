package com.jakub.zajac.feature.weather.presentation.location_search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jakub.zajac.feature.weather.domain.model.LocationModel

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    model: LocationModel,
    onItemClick: (model: LocationModel) -> Unit,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick.invoke(model) }) {
        Text(
            text = model.name, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }
}