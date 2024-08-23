package com.jakub.zajac.feature.weather.di

import com.jakub.zajac.common.storage.dao.LocationDao
import com.jakub.zajac.feature.weather.data.local.data_source.LocationLocalDataSource
import com.jakub.zajac.common.network.api.LocationApi
import com.jakub.zajac.feature.weather.data.remote.data_source.LocationRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideLocationRemoteDataSource(api: LocationApi): LocationRemoteDataSource =
        LocationRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideLocationLocalDataSource(
        locationDao: LocationDao
    ): LocationLocalDataSource = LocationLocalDataSource(locationDao)

}