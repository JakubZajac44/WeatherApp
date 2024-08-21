package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.domain.use_case.CacheSelectedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetCachedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.ValidateQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
    private val getLocationUseCase: GetLocationUseCase,
    private val getCachedLocationUseCase: GetCachedLocationUseCase,
    private val cacheSelectedLocationUseCase: CacheSelectedLocationUseCase,
    private val validateQueryUseCase: ValidateQueryUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<LocationSearchState> =
        MutableStateFlow(LocationSearchState())
    val state: StateFlow<LocationSearchState>
        get() = _state.asStateFlow()

    private var job: Job? = null

    init {
        getCachedLocationList()
    }

    fun onEvent(event: LocationSearchEvent) {

        when (event) {
            is LocationSearchEvent.SearchQueryTyped -> {
                if(!validateQueryUseCase.invoke(state.value.searchQuery.text, event.locationQuery.text)) return


                job?.cancel()

                job = viewModelScope.launch(Dispatchers.IO) {
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

            is LocationSearchEvent.LocationSelected -> {
                viewModelScope.launch(Dispatchers.IO) {
                    cacheSelectedLocationUseCase.invoke(event.locationModel)
                    getCachedLocationList()
                }

            }
        }
    }

    private fun getCachedLocationList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCachedLocationUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                cachedLocationList = result.data,
                                isLoading = false,
                                isSearching = false,
                                errorMessage = ""
                            )
                        }
                    }
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