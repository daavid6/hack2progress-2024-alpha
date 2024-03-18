package com.example.firelight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firelight.ui.FirelightViewModel
import com.example.firelight.ui.theme.FirelightTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private var viewModel: FirelightViewModel = FirelightViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirelightTheme {
                viewModel.initLocationClient(this)

                FirelightApp(viewModel = viewModel)
            }
        }
    }


}