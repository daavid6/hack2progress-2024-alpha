package com.example.firelight

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.firelight.data.EstadosSemaforo
import com.example.firelight.data.Riesgos
import com.example.firelight.data.SequedadVegetacion
import com.example.firelight.model.weather.MyData
import com.example.firelight.model.weather.MyLatLng
import com.example.firelight.model.weather.ValidData
import com.example.firelight.ui.FirelightViewModel
import com.example.firelight.ui.STATE
import com.example.firelight.ui.theme.FirelightTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.coroutineScope
import androidx.compose.foundation.layout.Column as Column

class UWU : ComponentActivity() {

    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var mainViewModel: FirelightViewModel
    private var locationRequired: Boolean = false
    private lateinit var coordinates: MyLatLng
    private var riesgo: Int = 0
    private val colores = arrayOf("Verde", "Amarillo", "Rojo")


    override fun onResume() {
        super.onResume()
        if (locationRequired) startLocationUpdate();
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        locationCallback.let{
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            )
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(3000)
                .setMaxUpdateAgeMillis(100)
                .build()

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }



    override fun onPause() {
        super.onPause()
        locationCallback.let {
            fusedLocationProviderClient.removeLocationUpdates(it)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLocationClient()
        initViewModel()

        setContent {
            //This to keep the value of the current location of the client
            var currentLocation by remember {
                mutableStateOf(MyLatLng(0.0,0.0))
            }

            //Implement location callback
            locationCallback = object: LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        currentLocation = MyLatLng(
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

    private fun fetchWeatherInformationCity(mainViewModel: FirelightViewModel, city: String) {
        mainViewModel.state = STATE.LOADING
        mainViewModel.getWeatherByCity(city)
        mainViewModel.state = STATE.SUCCESS
    }

    private fun fetchWeatherInformationCoords(mainViewModel: FirelightViewModel, currentLocation: MyLatLng) {
        mainViewModel.state = STATE.LOADING
        mainViewModel.getWeatherByLocation(currentLocation)
        mainViewModel.state = STATE.SUCCESS
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this@MainActivity)[FirelightViewModel::class.java]
    }




/*
    private @Composable
    fun WeatherSection(weatherResponse: WeatherResult) {
        return Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weatherResponse.toString())
        }
    }
*/
    @Composable
    private fun WeatherSection(weatherResponse: ValidData) {
        return Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dataExtracted = extractWeatherData(weatherResponse)
            val riesgo = dataExtracted?.let { Riesgos.riesgoActual(it.temp, it.speed, it.humidity, SequedadVegetacion.sequedadRandom()) }

            //Text(text = dataExtracted.toString())
            Text(
                fontSize = 60.sp,
                text = EstadosSemaforo.obtenColorSemaforo(riesgo) + "\n"+ "\n"+ "\n"+ "\n"+ dataExtracted?.name
            )
            Text(
                fontSize = 30.sp,
                text = "Temperatura: " +dataExtracted?.temp.toString() + "\n" + "\n" + "Humedad: "+ dataExtracted?.humidity.toString()
                        + "\n" + "\n" +"Velocidad del Viento: " + dataExtracted?.speed.toString()
            )

        }
    }

    @Composable
    private fun ErrorSection(errorMsg: String) {
        return Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = errorMsg, color = Color.White)
        }
    }

    @Composable
    private fun LoadingSection() {
        return Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.White )
        }
    }

    // Función para extraer los datos específicos
    private fun extractWeatherData(dataResponse: ValidData): MyData? {
        val temp = dataResponse.main?.temp
        val humidity = dataResponse.main?.humidity
        val speed = dataResponse.wind?.speed
        val name = dataResponse.name

        // Retorna un Triple con los valores extraídos, si algún valor es nulo retorna null
        return if (temp != null && humidity != null && speed != null && name != null) {
            MyData(temp, humidity, speed, name)
        } else {
            null
        }
    }











    private fun elegirBoton(coordinates: MyLatLng?, city: String?) {
        if(city != null) {
            fetchWeatherInformationCity(mainViewModel, city)
        } else  if(coordinates != null){
            fetchWeatherInformationCoords(mainViewModel, coordinates)
        } else {

        }
    }


    /*
    private fun obtenerCoordenadas(ciudad: String): Coord {

        var coord: Coord = mainViewModel.getCoordsByCity("Santander")

        return coord!!

    }
    */
}
