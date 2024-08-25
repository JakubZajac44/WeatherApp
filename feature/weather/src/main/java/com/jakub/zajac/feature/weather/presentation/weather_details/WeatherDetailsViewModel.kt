package com.jakub.zajac.feature.weather.presentation.weather_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.common.resource.SideEffect
import com.jakub.zajac.common.resource.handleApiError
import com.jakub.zajac.feature.weather.domain.use_case.GetCurrentWeatherUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetDailyWeatherUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetHourlyWeatherUseCase
import com.jakub.zajac.feature.weather.presentation.LocationSearchArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<WeatherDetailsState> =
        MutableStateFlow(WeatherDetailsState())
    val state: StateFlow<WeatherDetailsState>
        get() = _state.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<SideEffect>
        get() = _sideEffectChannel.receiveAsFlow()

    private val args = LocationSearchArg(savedStateHandle)


    init {
        _state.update {
            it.copy(
               locationName = args.locationName
            )
        }

        getWeatherDetails()
    }

    fun onEvent(event: WeatherDetailsEvent) {
        when (event) {
            WeatherDetailsEvent.RefreshWeatherData -> {
                _state.update {
                    it.copy(
                        isRefreshing = true,
                    )
                }
                getWeatherDetails()
            }
        }
    }

    private fun getWeatherDetails(){
        getCurrentWeather(args.locationKey)
        getHourlyWeather(args.locationKey)
        getDailyWeather(args.locationKey)
    }

    private fun getCurrentWeather(locationKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentWeatherUseCase(locationKey).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                currentWeather = null,
                                isLoadingWeatherCurrent = false,
                                isRefreshing = false
                            )
                        }
                        _sideEffectChannel.trySend(SideEffect.ShowToast(handleApiError(result.apiException)))
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                currentWeather = result.data,
                                isLoadingWeatherCurrent = false,
                                isRefreshing = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingWeatherCurrent = true,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getHourlyWeather(locationKey: String) {

        viewModelScope.launch(Dispatchers.IO) {

            getHourlyWeatherUseCase(locationKey).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                weatherHourly = listOf(),
                                isLoadingWeatherHourly = false,
                            )
                        }
                        _sideEffectChannel.trySend(SideEffect.ShowToast(handleApiError(result.apiException)))
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                weatherHourly = result.data,
                                isLoadingWeatherHourly = false,
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingWeatherHourly = true,
                            )
                        }
                    }
                }
            }
        }

    }

    private fun getDailyWeather(locationKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDailyWeatherUseCase.invoke(locationKey).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                weatherDaily = listOf(),
                                isLoadingWeatherDaily = false,
                            )
                        }
                        _sideEffectChannel.trySend(SideEffect.ShowToast(handleApiError(result.apiException)))
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                weatherDaily = result.data,
                                isLoadingWeatherDaily = false,
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingWeatherDaily = true,
                            )
                        }
                    }
                }
            }
        }
    }
}