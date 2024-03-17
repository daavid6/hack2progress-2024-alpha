package com.example.firelight

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firelight.data.ServiciosLocalicacion
import com.example.firelight.model.weather.MyLatLng
import com.example.firelight.ui.FirelightViewModel
import com.example.firelight.ui.layouts.AlertScreen
import com.example.firelight.ui.layouts.FormScreen
import com.example.firelight.ui.layouts.StartScreen
import com.example.firelight.ui.layouts.TrafficLightScreen

enum class FirelightScreen {
    Start,
    TrafficLight,
    Alert,
    Form
}

@Composable
fun FirelightApp(
    viewModel: FirelightViewModel = FirelightViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("Pruebas", "StartScreen")
    val uiState by viewModel.uiState.collectAsState()

    Log.d("Pruebas", "Random")
    viewModel.randomKnowledge()

    NavHost(
        navController = navController,
        startDestination = FirelightScreen.Start.name,
    ) {
        composable(route = FirelightScreen.Start.name) {
            StartScreen(
                helpText = uiState.knowledge,
                onLayoutButton = { navController.navigate(FirelightScreen.TrafficLight.name) },
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(route = FirelightScreen.TrafficLight.name) {

            ServiciosLocalicacion.getLocationName(coordenadas = MyLatLng(0.0, 0.0))

            TrafficLightScreen(
                address = uiState.address,
                onCambiarUbicionButton = { navController.navigate(FirelightScreen.Form.name) },
                onAlertButton = { navController.navigate(FirelightScreen.Alert.name) },
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(route = FirelightScreen.Alert.name) {
            AlertScreen(
                on112ButtonClicked = { navController.navigate(FirelightScreen.Start.name) },
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(route = FirelightScreen.Form.name) {
            FormScreen(
                onCambiarUbicionButton = { _, _, _ -> },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
