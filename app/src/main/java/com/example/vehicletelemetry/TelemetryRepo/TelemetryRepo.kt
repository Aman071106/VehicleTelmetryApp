package com.example.vehicletelemetry.TelemetryRepo

import android.util.Log
import com.example.vehicletelemetry.Backend.Location
import com.example.vehicletelemetry.Backend.Telemetry
import com.example.vehicletelemetry.retrofitModule.RequestInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

class TelemetryRepo @Inject constructor(private val requestInterface: RequestInterface) {

    private val _telemetryData = MutableStateFlow(
        Telemetry(0.0, Location(0.0, 0.0), 0.0, 0.0, 0.0)
    )
    val telemetryData: StateFlow<Telemetry> get() = _telemetryData

    init {
        startPolling()
    }

    private fun startPolling() {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                try {
                    getTelemetryData()
                } catch (e: Exception) {
                    Log.e("TelemetryRepo", "Polling error: ${e.message}")
                }
                delay(5000) // Fetch updates every 5 seconds
            }
        }
    }

    suspend fun getTelemetryData() {
        Log.d("TelemetryRepo", "Fetching telemetry data...")
        val response = requestInterface.getLastTrackOfVehicle()
        if (response.isSuccessful && response.body() != null) {
            val telemetryMap = response.body()
            val telemetry = telemetryMap?.values?.firstOrNull()
            telemetry?.let {
                _telemetryData.emit(it)
            }
            Log.d("TelemetryRepo", "Telemetry data: ${response.body()}")
        } else {
            Log.e("TelemetryRepo", "Error fetching telemetry data")
        }
    }
}
