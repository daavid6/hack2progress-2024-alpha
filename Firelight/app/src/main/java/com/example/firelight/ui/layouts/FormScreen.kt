package com.example.firelight.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firelight.R

@Composable
fun FormScreen(
    onCambiarUbicionButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorResource(R.color.grisFondo),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {

            Spacer(
                modifier.height(200.dp)
            )

            var city by remember { mutableStateOf("") }
            var latitude by remember { mutableStateOf("") }
            var longitude by remember { mutableStateOf("") }

            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Ciudad") }
            )

            TextField(
                value = latitude,
                onValueChange = { latitude = it },
                label = { Text("Latitud") }
            )

            TextField(
                value = longitude,
                onValueChange = { longitude = it },
                label = { Text("Longitud") }
            )

            Spacer(
                modifier.height(100.dp)
            )

            Row (
                modifier.weight(0.3F)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.grisBotones)
                    ),
                    onClick = onCambiarUbicionButton,
                    modifier = Modifier
                        .height(60.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = "Cambiar ubicacion",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormScreen(
        onCambiarUbicionButton = {}
    )
}