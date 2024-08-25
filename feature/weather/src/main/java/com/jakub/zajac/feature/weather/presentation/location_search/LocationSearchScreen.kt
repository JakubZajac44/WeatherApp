package com.jakub.zajac.feature.weather.presentation.location_search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakub.zajac.common.resource.SideEffect
import com.jakub.zajac.common.resource.SingleEventEffect
import com.jakub.zajac.common.resource.ui.dot_progress_bar.ProgressDotIndicator
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.presentation.location_search.component.LocationItem
import kotlinx.coroutines.flow.Flow

@Composable
fun LocationSearchScreen(
    modifier: Modifier = Modifier,
    state: LocationSearchState,
    event: (LocationSearchEvent) -> Unit,
    navigationEvent: (LocationSearchNavigationEvent) -> Unit,
    sideEffect: Flow<SideEffect>
) {
    var query by rememberSaveable { mutableStateOf("") }

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
        modifier = modifier.fillMaxSize()

    ) {

        Column {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(R.string.search_location_title),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = query,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onValueChange = { newQuery ->
                    query = newQuery
                    event.invoke(LocationSearchEvent.SearchQueryTyped(newQuery))
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                supportingText = {
                    Text(
                        text = state.inputErrorMessage.errorMessage.asString(),
                        style = TextStyle(fontSize = 12.sp),
                        maxLines = 2,
                        minLines = 2,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                isError = !state.inputErrorMessage.isQueryValid,
                trailingIcon = {
                    Box(Modifier.clickable {
                        query = ""
                        event.invoke(LocationSearchEvent.ClearSearchTyped)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = if (state.inputErrorMessage.isQueryValid) MaterialTheme.colorScheme.onSecondaryContainer
                            else MaterialTheme.colorScheme.error,
                            contentDescription = null
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    focusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.start_enter_location_name),
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 14.sp, color = MaterialTheme.colorScheme.inversePrimary
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                ),
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(if (state.isLoading) 10.dp else 0.dp),
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    if (state.locationList.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(vertical = 20.dp)
                        ) {
                            items(state.locationList) { location ->
                                LocationItem(
                                    modifier = Modifier.height(100.dp),
                                    model = location,
                                    backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                                ) { model ->
                                    event.invoke(
                                        LocationSearchEvent.LocationSelected(model)
                                    )

                                    navigationEvent.invoke(
                                        LocationSearchNavigationEvent.LocationSelected(
                                            model.key, model.name
                                        )
                                    )
                                }
                            }
                        }
                    } else if (state.locationNotFound) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 150.dp)
                                .padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.no_location_found)
                        )
                    } else if (state.cachedLocationList.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(vertical = 20.dp)
                        ) {
                            item {
                                ElevatedCard(
                                    modifier = modifier

                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        Image(
                                            painterResource(com.jakub.zajac.resource.R.drawable.ic_saved_search),
                                            "content description",
                                            modifier = Modifier
                                                .width(16.dp)
                                                .height(16.dp),
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer)
                                        )

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Text(
                                            modifier = Modifier,
                                            textAlign = TextAlign.Center,
                                            text = stringResource(R.string.last_search_result)
                                        )
                                    }


                                }
                            }

                            items(state.cachedLocationList) { location ->
                                LocationItem(
                                    modifier = Modifier.height(100.dp),
                                    model = location,
                                    backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                                ) { model ->

                                    event.invoke(
                                        LocationSearchEvent.LocationSelected(model)
                                    )

                                    navigationEvent.invoke(
                                        LocationSearchNavigationEvent.LocationSelected(
                                            model.key, model.name
                                        )
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 150.dp)
                                .padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.tertiary,
                            text = stringResource(R.string.no_historic_search)
                        )
                    }

                }

                if (state.isLoading) {
                    Box(
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .fillMaxSize()
                            .testTag(stringResource(R.string.loading_progress_tag)),
                    ) {
                        ProgressDotIndicator(
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.TopCenter),
                            progressColor = MaterialTheme.colorScheme.inverseSurface,
                        )
                    }

                }
            }
        }
    }
}