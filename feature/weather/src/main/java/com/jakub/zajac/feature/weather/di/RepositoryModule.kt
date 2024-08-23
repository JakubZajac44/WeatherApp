package com.jakub.zajac.feature.weather.di

import com.jakub.zajac.feature.weather.data.repository.LocationRepositoryImpl
import com.jakub.zajac.feature.weather.data.repository.WeatherRepositoryImpl
import com.jakub.zajac.feature.weather.domain.repository.LocationRepository
import com.jakub.zajac.feature.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    @Singleton
    abstract fun provideWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

}