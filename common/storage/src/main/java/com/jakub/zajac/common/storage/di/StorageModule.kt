package com.jakub.zajac.common.storage.di

import android.content.Context
import com.jakub.zajac.common.storage.dao.LocationDao
import com.jakub.zajac.common.storage.db.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideWeatherDataBase(@ApplicationContext context: Context): WeatherDataBase =
       WeatherDataBase.getDatabase(context)

    @Provides
    @Singleton
    fun provideLocationDao(dataBase: WeatherDataBase): LocationDao = dataBase.locationDao()
}