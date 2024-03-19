package com.example.firelight.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firelight.R
import com.example.firelight.data.FirelightUiState
import com.example.firelight.data.ServiciosLocalicacion
import com.example.firelight.model.weather.Coordenadas
import com.example.firelight.network.RetrofitClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class State {
    LOADING,
    SUCCESS,
    FAILED
}

class FirelightViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FirelightUiState())
    val uiState: StateFlow<FirelightUiState> = _uiState.asStateFlow()

    fun getWeatherByLocation(coordenadas: Coordenadas) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = State.LOADING)
            val apiService = RetrofitClient.getInstance()
            try {
                val apiResponse = apiService.getWeatherByCoord(coordenadas.latitud, coordenadas.longitud)

                _uiState.value = _uiState.value.copy(state = State.SUCCESS, dataResponse = apiResponse)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(state = State.FAILED)
            }
        }
    }

    fun getWeatherByLocation(city: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(state = State.LOADING)
            val apiService = RetrofitClient.getInstance()
            try {
                val apiResponse = apiService.getWeatherByCity(city)

                _uiState.value = _uiState.value.copy(state = State.SUCCESS, dataResponse = apiResponse)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(state = State.FAILED)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun initLocationClient(context: Context): Coordenadas {

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val coordenadas = Coordenadas(
            latitud = fusedLocationProviderClient.lastLocation.result.latitude,
            longitud = fusedLocationProviderClient.lastLocation.result.longitude
        )

        _uiState.value = _uiState.value.copy(fusedLocationProviderClient = fusedLocationProviderClient, currentLocation = coordenadas)

        return coordenadas
    }
/*
    fun removeLocationUpdates(locationCallback: LocationCallback) {
        _uiState.value.fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }
*/
    fun startLocationUpdate() {
        _uiState.value = _uiState.value.copy(locationRequired = true)
    }

/*    fun updateLocation() {
        object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                for (location in p0.locations) {
                    _uiState.value = _uiState.value.copy(currentLocation = Coordenadas(location.latitude, location.longitude))
                }
            }
        }
    }
*/
    fun getAddresses(): String {
        val address = ServiciosLocalicacion.getLocationName(coordenadas = uiState.value.currentLocation)

        _uiState.value = _uiState.value.copy(address = address)

        return address
    }

/*
    private fun fetchWeatherInformation(mainViewModel: FirelightViewModel, city: String) {
        _uiState.value = _uiState.value.copy(state = State.LOADING)
        mainViewModel.getWeatherByLocation(city)
        _uiState.value = _uiState.value.copy(state = State.SUCCESS)
    }

    private fun fetchWeatherInformation(mainViewModel: FirelightViewModel, currentLocation: Coordenadas) {
        _uiState.value = _uiState.value.copy(state = State.LOADING)
        mainViewModel.getWeatherByLocation(currentLocation)
        _uiState.value = _uiState.value.copy(state = State.SUCCESS)
    }
*/

    fun randomKnowledge(): Int {
        val num = 1..9
        val randomNumber = num.random()

        @StringRes val knowledge: Int = when (randomNumber) {
            1 -> R.string.consejo1
            2 -> R.string.consejo2
            3 -> R.string.consejo3
            4 -> R.string.consejo4
            5 -> R.string.consejo5
            6 -> R.string.consejo6
            7 -> R.string.consejo7
            8 -> R.string.consejo8
            9 -> R.string.consejo9
            else -> R.string.consejo10
        }

        _uiState.value = _uiState.value.copy(knowledge = knowledge)

        return randomNumber
    }
}