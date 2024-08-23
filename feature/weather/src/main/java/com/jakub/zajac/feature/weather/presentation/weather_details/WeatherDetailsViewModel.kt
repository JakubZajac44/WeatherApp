package com.jakub.zajac.feature.weather.presentation.weather_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.zajac.common.resource.Resource
import com.jakub.zajac.feature.weather.domain.use_case.GetDailyWeatherUseCase
import com.jakub.zajac.feature.weather.domain.use_case.GetHourlyWeatherUseCase
import com.jakub.zajac.feature.weather.presentation.LocationKeyArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase
) : ViewModel() {

    private val args = LocationKeyArg(savedStateHandle)


    init {
        getCurrentHourlyWeather(args.locationKey)
    }

    private fun getCurrentHourlyWeather(locationKey: String) {

        viewModelScope.launch(Dispatchers.IO) {

            getHourlyWeatherUseCase(locationKey).collect { result ->
                when (result) {
                    is Resource.Error -> {
                    }

                    is Resource.Success -> {
                        getDailyWeather(locationKey)
                    }

                    is Resource.Loading -> {

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
                    }

                    is Resource.Success -> {
                        result.data.forEach {
                        }


                    }

                    is Resource.Loading -> {
                    }
                }
            }


        }


    }
}