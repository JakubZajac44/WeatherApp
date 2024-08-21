package com.jakub.zajac.common.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    @PrimaryKey
    val key: String,
    val name: String,
    val type: String,
    val country: String,
    val usageNumber: Int
)
