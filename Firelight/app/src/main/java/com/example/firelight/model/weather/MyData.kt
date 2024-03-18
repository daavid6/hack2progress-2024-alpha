package com.example.firelight.model.weather

data class MyData(
    val name: String,
    val coordenadas: Coordenadas,
    val temp: Double,
    val humidity: Double,
    val windSpeed: Double,
) {
    override fun toString(): String {
        return "Nombre: $name \n" +
               "Coordenadas: $coordenadas \n" +
               "Temperatura: $temp \n" +
               "Humedad: $humidity \n" +
               "Velocidad del Viento: $windSpeed \n"
    }
}
