package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.common.resource.ui.dot_progress_bar.ProgressDotIndicator
import com.jakub.zajac.feature.weather.presentation.location_search.component.LocationItem

@Composable
fun LocationSearchScreen(
    modifier: Modifier = Modifier,
    state: LocationSearchState,
    event: (LocationSearchEvent) -> Unit,
    navigationEvent: (LocationSearchNavigationEvent) -> Unit
) {
    var query by rememberSaveable  { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

        Column {

            OutlinedTextField(
                value = query,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newQuery ->
                    query = newQuery
                    event.invoke(LocationSearchEvent.SearchQueryTyped(newQuery))
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                supportingText = {
                    if (state.inputErrorMessage.isNotBlank()) {
                        Text(state.inputErrorMessage)
                    }

                },
                isError = true,
                trailingIcon = {
                    Box(Modifier.clickable {
                        query = ""
                        event.invoke(LocationSearchEvent.ClearSearchTyped)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = null
                        )
                    }

                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                ),
                placeholder = {
                    Text(
                        text = "Enter your text",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 14.sp, color = Color.LightGray
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                ),
            )


            ProgressDotIndicator(
                modifier = Modifier.size(80.dp),
                progressColor = MaterialTheme.colorScheme.primary,
            )

            if (state.locationList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(state.locationList) { location ->
                        LocationItem(
                            model = location,
                        ) { model ->
                            event.invoke(
                                LocationSearchEvent.LocationSelected(model)
                            )

                            navigationEvent.invoke(
                                LocationSearchNavigationEvent.LocationSelected(
                                    model.key
                                )
                            )
                        }
                    }
                }
            }
            else if(state.locationNotFound){
                Text("Brak dopasowania ")
            }
            else if (state.cachedLocationList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(state.cachedLocationList) { location ->
                        LocationItem(
                            model = location,
                        ) { model ->

                            event.invoke(
                                LocationSearchEvent.LocationSelected(model)
                            )

                            navigationEvent.invoke(
                                LocationSearchNavigationEvent.LocationSelected(
                                    model.key
                                )
                            )
                        }
                    }
                }
            }
            else {
                Text(text = "Zacznij wpisywać aby pobrać listę miast")
            }
        }
    }
}