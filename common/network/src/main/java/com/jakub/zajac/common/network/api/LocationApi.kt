package com.jakub.zajac.common.network.api

import com.jakub.zajac.common.network.model.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("locations/v1/cities/autocomplete")
    suspend fun getLocationsByName(
        @Query("q") queryName: String
    ): Response<List<LocationDto>>
}