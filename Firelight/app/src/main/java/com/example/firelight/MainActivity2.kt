package com.example.firelight

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.firelight.model.weather.Coordenadas
import com.example.firelight.ui.FirelightViewModel
import com.example.firelight.ui.theme.FirelightTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.sample

class UWU : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLocationClient()
        initViewModel()

        setContent {
            //This to keep the value of the current location of the client
            var currentLocation by remember {
                mutableStateOf(Coordenadas(0.0,0.0))
            }

            //Implement location callback
            locationCallback = object: LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        currentLocation = Coordenadas(
                            location.latitude,
                            location.longitude
                        )
                    }


                    //Fetch API when location change
                    //fetchWeatherInformationCoords(mainViewModel, currentLocation)


                    //Fetch API when insert city
                    //fetchWeatherInformationCity(mainViewModel, "Santander")


                    //Fetch API when insert coordenates
                    //coordinates = MyLatLng(43.4647, -3.8044)
                    //fetchWeatherInformationCoords(mainViewModel, coordinates)

                    //Coordenadas no ciudad
                    //elegirBoton(currentLocation, null)
                    //Ciudad
                    elegirBoton(currentLocation, "León")
                    //León



                }

            }


            FirelightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //Create a simple interface for the location screen
                    LocationScreen(currentLocation)

                }
            }
        }
    }

    private fun fetchWeatherInformation(mainViewModel: FirelightViewModel, city: String) {
        mainViewModel.uiState.value.state = STATE.LOADING
        mainViewModel.getWeatherByCity(city)
        mainViewModel.state = STATE.SUCCESS
    }

    private fun fetchWeatherInformation(mainViewModel: FirelightViewModel, currentLocation: Coordenadas) {
        mainViewModel.state = STATE.LOADING
        mainViewModel.getWeatherByLocation(currentLocation)
        mainViewModel.state = STATE.SUCCESS
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this@MainActivity)[FirelightViewModel::class.java]
    }



    private fun elegirBoton(coordinates: Coordenadas?, city: String?) {
        if(city != null) {
            fetchWeatherInformationCity(mainViewModel, city)
        } else  if(coordinates != null){
            fetchWeatherInformationCoords(mainViewModel, coordinates)
        }
    }
}
