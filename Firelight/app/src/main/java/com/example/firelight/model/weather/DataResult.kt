package com.example.firelight.model.weather

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("name") var name: String? = null,
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("humidity") var humidity: Double? = null,
    @SerializedName("speed") var windSpeed: Double? = null
) {
    fun toMyData(): MyData {
        return MyData(
            name = name ?: "No disponible",
            coordenadas = Coordenadas(latitud = lat ?: 0.0, longitud = lon ?: 0.0),
            temp = temp ?: 0.0,
            humidity = humidity ?: 0.0,
            windSpeed = windSpeed ?: 0.0
        )
    }
}

