package com.example.firelight.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firelight.model.weather.MyLatLng
import com.example.firelight.network.RetrofitClient
import com.example.firelight.R
import com.example.firelight.data.FirelightUiState
import com.example.firelight.model.weather.ValidData
import com.example.firelight.model.weather.WeatherResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class STATE {
    LOADING,
    SUCCESS,
    FAILED
}

class FirelightViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FirelightUiState())
    val uiState: StateFlow<FirelightUiState> = _uiState.asStateFlow()

    //Hold value from API for weather info
    var weatherResponse : WeatherResult by mutableStateOf(WeatherResult())
    var state by mutableStateOf(STATE.LOADING) //Control state of view model
    var dataResponse : ValidData by mutableStateOf(ValidData())

    var errorMsg: String by mutableStateOf("")
    /*
      fun getWeatherByLocation(latLng: MyLatLng) {
          viewModelScope.launch {
              state = STATE.LOADING
              val apiService = RetrofitClient.getInstance()
              try {
                  val apiResponse = apiService.getWeather(latLng.lat, latLng.lng)
                  weatherResponse = apiResponse //Update state
                  state = STATE.SUCCESS
              } catch (e: Exception) {
                  errorMsg = e.message!!.toString()
                  state = STATE.FAILED
              }
          }
      }
          */
    fun getWeatherByLocation(latLng: MyLatLng) {
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstance()
            try {
                val apiResponse = apiService.getWeather(latLng.lat, latLng.lng)
                dataResponse = apiResponse //Update state
                state = STATE.SUCCESS
            } catch (e: Exception) {
                errorMsg = e.message!!.toString()
                state = STATE.FAILED
            }
        }
    }

    fun getWeatherByCity(city: String) {
        viewModelScope.launch {
            state = STATE.LOADING
            val apiService = RetrofitClient.getInstance()
            try {
                val apiResponse = apiService.getWeather(city)
                dataResponse = apiResponse
                state = STATE.SUCCESS
            } catch (e: Exception) {
                errorMsg = e.message!!.toString()
                state = STATE.FAILED
            }
        }
    }

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