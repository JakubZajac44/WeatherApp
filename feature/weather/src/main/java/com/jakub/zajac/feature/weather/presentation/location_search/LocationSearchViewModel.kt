package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.domain.use_case.CacheSelectedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetCachedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.ValidateQueryUseCase
import com.jakub.zajac.feature.weather.domain.use_case.ValidationQueryStatus
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
                handleNewSearchQuery(event.locationQuery)
            }

            LocationSearchEvent.ClearSearchTyped -> {
                _state.update { data ->
                    data.copy(
                        locationList = listOf(),
                        isLoading = false,
                        inputErrorMessage = "",
                        locationNotFound = false
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
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleNewSearchQuery(locationQuery: String) {
        val validateQueryMessage = validateSearchQueryMessageError(locationQuery)
        val shouldLocationSearch = validateQueryMessage.isEmpty()

        _state.update { data ->
            data.copy(
                inputErrorMessage = validateQueryMessage
            )
        }

        job?.cancel()
        if (locationQuery.isEmpty()) {
            _state.update { data ->
                data.copy(
                    locationList = listOf(), locationNotFound = false
                )
            }

        } else if (shouldLocationSearch) {
            getLocationByQueryName(locationQuery)
        }

    }

    private fun validateSearchQueryMessageError(newQuery: String): String {
        return when (validateQueryUseCase.invoke(newQuery)) {
            ValidationQueryStatus.QueryCorrect -> {
                ""
            }

            ValidationQueryStatus.QueryContainNumber -> {
                "Nazwa miejscowości nie może zawierać liczb"
            }

            ValidationQueryStatus.QueryContainSpecialChar -> {

                "Nazwa miejscowości nie może zawierać zanków specjalnych"
            }

            ValidationQueryStatus.QueryDefault -> {
                "Błędna nazwa miejscowości"
            }
        }
    }

    private fun getLocationByQueryName(queryName: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(SEARCHING_DELAY)
            getLocationUseCase(queryName).collect { result ->
                when (result) {
                    is Resource.Error -> {
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                locationList = result.data,
                                locationNotFound = result.data.isEmpty(),
                                isLoading = false,
                                isSearching = false,
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

    companion object{
        const val SEARCHING_DELAY = 300L
    }
}