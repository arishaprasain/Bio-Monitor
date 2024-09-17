package com.example.firebaseauth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.4.1" // Replace with your ESP32's IP


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val sensorApi: SensorApiService = retrofit.create(SensorApiService::class.java)
}

