package com.jakub.zajac.feature.weather.presentation.location_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.common.resource.SideEffect
import com.jakub.zajac.common.resource.UiText
import com.jakub.zajac.common.resource.handleApiError
import com.jakub.zajac.feature.weather.R
import com.jakub.zajac.feature.weather.domain.use_case.CacheSelectedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetCachedLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetLocationUseCase
import com.jakub.zajac.feature.weather.domain.use_case.ValidateQueryUseCase
import com.jakub.zajac.feature.weather.domain.use_case.ValidationQueryStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<SideEffect>
        get() = _sideEffectChannel.receiveAsFlow()

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
                        inputErrorMessage = ValidationResult(),
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
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleNewSearchQuery(locationQuery: String) {
        val validateQueryStatus = validateSearchQueryMessageError(locationQuery)

        _state.update { data ->
            data.copy(
                inputErrorMessage = validateQueryStatus,
                isLoading = validateQueryStatus.isQueryValid
            )
        }

        job?.cancel()
        if (locationQuery.isEmpty()) {
            _state.update { data ->
                data.copy(
                    locationList = listOf(), locationNotFound = false, isLoading = false
                )
            }

        } else if (validateQueryStatus.isQueryValid) {
            getLocationByQueryName(locationQuery)
        }
    }

    private fun validateSearchQueryMessageError(newQuery: String): ValidationResult {
        return when (validateQueryUseCase.invoke(newQuery)) {
            ValidationQueryStatus.QueryCorrect -> {
                ValidationResult(
                    isQueryValid = true, errorMessage = UiText.DynamicString("")
                )
            }

            ValidationQueryStatus.QueryContainNumber -> {
                ValidationResult(
                    isQueryValid = false,
                    errorMessage = UiText.StringResource(R.string.validation_query_error_contain_number)
                )
            }

            ValidationQueryStatus.QueryContainSpecialChar -> {
                ValidationResult(
                    isQueryValid = false,
                    errorMessage = UiText.StringResource(R.string.validation_query_error_contain_special_char)
                )
            }

            ValidationQueryStatus.QueryDefault -> {
                ValidationResult(
                    isQueryValid = false,
                    errorMessage = UiText.StringResource(R.string.validation_query_error_default)
                )
            }
        }
    }

    private fun getLocationByQueryName(queryName: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(SEARCHING_DELAY)
            getLocationUseCase(queryName).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                locationList = listOf(),
                                isLoading = false,
                            )
                        }
                        _sideEffectChannel.trySend(SideEffect.ShowToast(handleApiError(result.apiException)))
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                locationList = result.data,
                                locationNotFound = result.data.isEmpty(),
                                isLoading = false,
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

    companion object {
        const val SEARCHING_DELAY = 300L
    }
}