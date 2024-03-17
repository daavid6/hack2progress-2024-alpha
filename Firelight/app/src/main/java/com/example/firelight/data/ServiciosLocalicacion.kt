package com.example.firelight.data

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.firelight.model.weather.MyLatLng
import com.example.firelight.ui.STATE
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.coroutineScope
import android.location.Geocoder
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class ServiciosLocalicacion {

    class GeocodingResponse(
        val results: List<String>
    )


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    fun initLocationClient(context: Context) {
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    }


    companion object {
        fun getLocationName(coordenadas: MyLatLng): String {
            val apiKeyGeocodiing = "YOUR_API_KEY" // Replace with your actual API key
            val response =
                URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=${coordenadas.lat},${coordenadas.lng}&key=$apiKeyGeocodiing").readText()

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
    private fun LocationScreen(currentLocation: MyLatLng) {

        //Request runtime permission
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionMap ->
            val areGrated = permissionMap.values.reduce {
                    accepted, next -> accepted && next
            }
            //Check all permission is acept
            if(areGrated){
                locationRequired = true;
                startLocationUpdate();
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
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
                if (permissions.all{
                        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                    }) {
                    //All permissions accepted
                    startLocationUpdate()
                }
                else {
                    launcherMultiplePermissions.launch(permissions)
                }
            }
        })

        //Esta parte cambiar con lo de david, importante como coge la info de la api
        val gradient = Brush.linearGradient(
            colors = listOf(Color.White, Color.Blue),
            start = Offset(1000f, -1000f),
            end = Offset(000f, -1000f)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val marginTop = screenHeight * 0.2f //Margen de 20%
            val marginTopPx = with(LocalDensity.current) {marginTop.toPx()}

            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)

                        //Define layout
                        layout(
                            placeable.width,
                            placeable.height + marginTopPx.toInt()
                        ) {
                            placeable.placeRelative(0, marginTopPx.toInt())
                        }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mainViewModel.state == STATE.LOADING) {
                    LoadingSection()
                }
                else if(mainViewModel.state == STATE.FAILED) {
                    ErrorSection(mainViewModel.errorMsg)
                }
                else {
                    WeatherSection(mainViewModel.dataResponse)
                }
            }
        }


        //Text(text = "${currentLocation.lat}/${currentLocation.lng}")
    }
}


