package com.example.firelight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@SuppressLint("MissingPermission")
@Composable
fun FirelightApp(
    viewModel: FirelightViewModel = FirelightViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    /*Inintialize the viewModel and other values*/
    viewModel.randomKnowledge()
    viewModel.getWeatherByLocation(uiState.currentLocation)
    viewModel.getAddresses()

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

            val address = uiState.dataResponse.name ?: "No location"

            TrafficLightScreen(
                address = address,
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
