package com.jakub.zajac.feature.weather.presentation.weather_info

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jakub.zajac.feature.weather.presentation.LocationKeyArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val args = LocationKeyArg(savedStateHandle)


    init {
        Log.e("Test", "Location key ${args.locationKey}")
    }
}