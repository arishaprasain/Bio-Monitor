package com.example.firebaseauth

import retrofit2.http.GET
import retrofit2.Call

data class SensorData(
    val BPM: Int,
    val SpO2: Int
)

interface SensorApiService {
    @GET("/sensor")
    fun getSensorData(): Call<SensorData>
}
