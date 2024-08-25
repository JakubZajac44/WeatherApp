package com.jakub.zajac.feature.weather.data.data_source.local

import com.jakub.zajac.common.storage.dao.LocationDao
import com.jakub.zajac.common.storage.model.LocationEntity
import javax.inject.Inject

class LocationLocalDataSource @Inject constructor(
    private val locationDao: LocationDao
){
    suspend fun getLocationList() = locationDao.getAllLocation()

    suspend fun insertLocation(location: LocationEntity) = locationDao.insertLocation(location)
}