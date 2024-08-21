package com.jakub.zajac.common.storage.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jakub.zajac.common.storage.model.LocationEntity

@Dao
interface LocationDao {

    @Upsert
    suspend fun insertLocation(location: LocationEntity)

    @Query("Select * from LocationEntity")
    suspend fun getAllLocation(): List<LocationEntity>

}
