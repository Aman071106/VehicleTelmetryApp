package com.example.vehicletelemetry.Backend

data class Telemetry(
    val battery: Double,
    val location: Location,
    val speed: Double,
    val temperature: Double,
    val tirePressure:Double
)