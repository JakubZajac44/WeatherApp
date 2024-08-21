package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.domain.use_case.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<LocationSearchState> =
        MutableStateFlow(LocationSearchState())
    val state: StateFlow<LocationSearchState>
        get() = _state.asStateFlow()

    private var job: Job? = null

    fun onEvent(event: LocationSearchEvent) {

        when (event) {
            is LocationSearchEvent.SearchQueryTyped -> {
                job?.cancel()

                job = viewModelScope.launch(Dispatchers.Main) {
                    _state.update { data ->
                        data.copy(
                            searchQuery = event.locationQuery, isSearching = true
                        )
                    }
                    delay(500L)
                    getLocationByQueryName(event.locationQuery.text)
                }
            }

            LocationSearchEvent.ClearSearchTyped -> {
                _state.update { data ->
                    data.copy(
                        searchQuery = TextFieldValue(""), locationList = listOf(), isLoading = false
                    )
                }
            }
        }
    }


    private fun getLocationByQueryName(queryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase(queryName).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    locationList = result.data,
                                    isLoading = false,
                                    isSearching = false,
                                    errorMessage = ""
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoading = true,
                                )
                            }
                        }
                    }
                }
        }
    }
}