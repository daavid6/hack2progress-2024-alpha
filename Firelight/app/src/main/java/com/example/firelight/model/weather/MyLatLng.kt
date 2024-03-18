package com.example.firelight.model.weather

class Coordenadas(
    val latitud: Double,
    val longitud: Double
) {
    override fun toString(): String {
        return "Latitud: $latitud \nLongitud: $longitud"
    }
}
