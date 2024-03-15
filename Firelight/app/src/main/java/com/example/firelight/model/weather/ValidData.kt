package com.example.firelight.model.weather

import com.google.gson.annotations.SerializedName

data class ValidData(
    @SerializedName("coord") var coord: Coord? = Coord(), //Coordenates
    @SerializedName("main") var main: Main? = Main(), //All about tempeatures
    @SerializedName("wind") var wind: Wind? = Wind(), //Speed and orientation(degrees)
    @SerializedName("name") var name: String? = null
)
