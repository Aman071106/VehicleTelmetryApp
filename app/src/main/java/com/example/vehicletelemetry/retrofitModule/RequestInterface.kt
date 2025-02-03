package com.example.vehicletelemetry.retrofitModule

import com.example.vehicletelemetry.Backend.Telemetry
import retrofit2.Response
import retrofit2.http.GET


interface RequestInterface {

    @GET("telemetry.json?orderBy=\"%24key\"&limitToLast=1")
    suspend fun getLastTrackOfVehicle(
//        @Query("orderBy") orderBy: String = "\"%24key\"",
//        @Query("limitToLast") limitToLast: Int = 1
    ): Response<Map<String, Telemetry>>
}
