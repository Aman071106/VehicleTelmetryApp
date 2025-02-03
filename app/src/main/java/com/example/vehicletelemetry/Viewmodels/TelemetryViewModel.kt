package com.example.vehicletelemetry.Viewmodels

import androidx.lifecycle.ViewModel
import com.example.vehicletelemetry.Backend.Telemetry
import com.example.vehicletelemetry.TelemetryRepo.TelemetryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TelemetryViewModel @Inject constructor(
    private val telemetryRepo: TelemetryRepo
) : ViewModel() {

    val telemetry: StateFlow<Telemetry> get() = telemetryRepo.telemetryData
}
