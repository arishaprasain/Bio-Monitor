package com.example.firebaseauth





import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.await

class SensorViewModel : ViewModel() {
    var bpm = mutableStateOf(0)
        private set
    var spo2 = mutableStateOf(0)
        private set

    // Lists to store the history of values
    val bpmHistory = mutableStateListOf<Pair<Float, Float>>()
    val spo2History = mutableStateListOf<Pair<Float, Float>>()

    val bpmLst= mutableStateListOf<Float>()
    val spo2Lst = mutableStateListOf<Float>()


    private var timeIndex = 0f // Use this for x-axis

    init {
        fetchSensorDataPeriodically()
    }

    private fun fetchSensorDataPeriodically() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                try {3
                    val response = RetrofitClient.sensorApi.getSensorData().await()
                    // Log the raw JSON response (using toString for simplicity)
                    Log.d("SensorViewModel", "JSON Response: $response")
                    bpm.value = response.BPM
                    spo2.value = response.SpO2

                    //logcat check
                    Log.d("BBBPPPMMM: ", bpm.toString())
                    Log.d("sp02: ", spo2.toString())

                    // Add entries to history




                    // Add to lists with conditional check
                    if (bpm.value >= 40) {
                        bpmLst.add(bpm.value.toFloat())
                        bpmHistory.add(Pair(timeIndex, response.BPM.toFloat()))
                        timeIndex += 1f // Increment timeIndex for each new entry
                    }
                    if (spo2.value >= 40) {
                        spo2Lst.add(spo2.value.toFloat())
                        spo2History.add(Pair(timeIndex, response.SpO2.toFloat()))
                        timeIndex += 1f // Increment timeIndex for each new entry
                    }

                } catch (e: Exception) {
                    // Log any errors encountered
                    Log.e("SensorViewModel", "Error fetching sensor data", e)
                    e.printStackTrace()
                }
                delay(1000) // Wait 1 second before next request
            }
        }
    }
}


