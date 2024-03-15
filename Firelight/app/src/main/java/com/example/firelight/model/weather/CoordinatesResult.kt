package com.example.firelight.model.weather

import com.google.gson.annotations.SerializedName

data class CoordinatesResult(
    @SerializedName("coord") var coord: Coord? = Coord()
)
