package com.example.firelight.data

import androidx.annotation.StringRes
import com.example.firelight.R
import com.example.firelight.model.weather.Coordenadas
import com.example.firelight.model.weather.DataResult
import com.example.firelight.ui.State
import com.google.android.gms.location.FusedLocationProviderClient

data class FirelightUiState(
    @StringRes var knowledge: Int = R.string.consejo1,
    val address: String = "",
    var currentLocation: Coordenadas = Coordenadas(0.0,0.0),
    val semaforo: EstadosSemaforo = EstadosSemaforo.VERDE,
    val state: State = State.LOADING,
    var dataResponse: DataResult = DataResult(),
    var errorMsg: String = "",
    var locationRequired: Boolean = false,
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
)