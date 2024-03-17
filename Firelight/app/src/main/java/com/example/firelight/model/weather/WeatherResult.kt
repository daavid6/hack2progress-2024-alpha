package com.example.firelight.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherResult(
    @SerializedName("coord") var coord: Coord? = Coord(), //Coordenates
    @SerializedName("weather") var weather: ArrayList<Weather>? = arrayListOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: Main? = Main(), //All about tempeatures
    @SerializedName("visibility") var visibility: Int? = null, //Number of visibility
    @SerializedName("wind") var wind: Wind? = Wind(), //Speed and orientation(degrees)
    @SerializedName("dt") var dt: String? = null, //datetime
    @SerializedName("sys") var sys: Sys? = Sys(), //Sun info
    @SerializedName("timezone") var timezone: Int? = null, //Usage?
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Int? = null,
    //@SerializedName("snow") var snow: Snow? = Snow(), //No collect by the gson?
)
