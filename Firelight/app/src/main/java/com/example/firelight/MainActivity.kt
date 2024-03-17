package com.example.firelight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firelight.data.ServiciosLocalicacion
import com.example.firelight.model.weather.MyLatLng
import com.example.firelight.ui.theme.FirelightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirelightTheme {
                FirelightApp()
            }
        }
    }
}