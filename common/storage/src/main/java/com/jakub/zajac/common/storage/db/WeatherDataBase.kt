package com.jakub.zajac.common.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jakub.zajac.common.storage.dao.LocationDao
import com.jakub.zajac.common.storage.model.LocationEntity


@Database(
    entities = [LocationEntity::class], version = 1
)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {

        private const val DATABASE_NAME = "weather_db"

        fun getDatabase(context: Context): WeatherDataBase {
            return Room.databaseBuilder(
                context.applicationContext, WeatherDataBase::class.java, DATABASE_NAME
            ).build()
        }
    }
}