package com.example.firelight.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.firelight.constants.Const
import com.example.firelight.model.weather.Coordenadas
import com.example.firelight.model.weather.MyData
import com.example.firelight.ui.FirelightViewModel
import com.example.firelight.ui.State
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import java.net.URL

class ServiciosLocalicacion {

    class GeocodingResponse(
        val results: List<String>
    )

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )


    companion object {
        fun getLocationName(coordenadas: Coordenadas): String {
            val apiKeyGeocodiing = Const.OPEN_WEATHER_MAP_API_KEY // Replace with your actual API key
            val response =
                URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=${coordenadas.latitud},${coordenadas.longitud}&key=$apiKeyGeocodiing").readText()

            // Parse the response with Gson
            val geocodingResponse = Gson().fromJson(response, GeocodingResponse::class.java)

            // This will give us the complete address.
            return if (geocodingResponse.results.isNotEmpty()) {
                geocodingResponse.results[0]
            } else {
                "Location name not found"
            }
        }
    }


    @Composable
    private fun LocationScreen(currentLocation: Coordenadas, viewModel: FirelightViewModel, context: Context) {

        val uiState by viewModel.uiState.collectAsState()

        //Request runtime permission
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionMap ->
            val areGrated = permissionMap.values.reduce {
                    accepted, next -> accepted && next
            }
            //Check all permission is acept
            if(areGrated){
                uiState.locationRequired = true;
                viewModel.startLocationUpdate();
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }

        val systemUIController = rememberSystemUiController()

        DisposableEffect(key1 = true, effect ={
            systemUIController.isSystemBarsVisible = false //Hide status bar
            onDispose{
                systemUIController.isSystemBarsVisible = true //Show status bar
            }
        })

        LaunchedEffect(key1 = currentLocation, block = {
            coroutineScope {
                if (permissions.all{ permission -> ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED }) {
                    //All permissions accepted
                    viewModel.startLocationUpdate()
                }
                else {
                    launcherMultiplePermissions.launch(permissions)
                }
            }
        })

        when (uiState.state) {
            State.LOADING -> { LoadingSection() }
            State.FAILED -> { ErrorSection() }
            else -> { WeatherSection(uiState.dataResponse.toMyData()) }
        }
    }




    @Composable
    private fun WeatherSection(dataExtracted: MyData?) {
        return Column {
            if (dataExtracted == null) {
                ErrorSection()
                return
            }

            val riesgo = Riesgos.riesgoActual(
                temperatura = dataExtracted.temp,
                viento = dataExtracted.windSpeed,
                humedad = dataExtracted.humidity,
                sequedad = SequedadVegetacion.sequedadRandom()
            )

            val colorSemaforo = EstadosSemaforo.obtenColorSemaforo(riesgo)

            if (colorSemaforo == null) {
                ErrorSection()
                return
            }

            Text(
                fontSize = 60.sp,
                text = colorSemaforo.name
            )
            Text(
                fontSize = 30.sp,
                text = dataExtracted.toString()
            )
        }
    }

    @Composable
    private fun ErrorSection() {
        return Column {
            Text(text = "Error", color = Color.White)
        }
    }

    @Composable
    private fun LoadingSection() {
        return Column {
            CircularProgressIndicator(color = Color.White )
        }
    }
}


