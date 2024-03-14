package com.example.firelight.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firelight.R
import com.example.firelight.ui.components.MainButton

@Composable
fun FormScreen(
    onCambiarUbicionButton: (String?, String?, String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorResource(R.color.grisFondo),
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp, 32.dp, 32.dp, 48.dp)
        ){
            var city by remember { mutableStateOf("") }
            var latitude by remember { mutableStateOf("") }
            var longitude by remember { mutableStateOf("") }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.925F)
            ){

                Spacer(modifier = Modifier.weight(0.1F))

                Box (
                    modifier = Modifier.weight(0.1F)
                ){
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("Ciudad") },

                    )
                }

                Box (
                    modifier = Modifier.weight(0.1F)
                ){
                    Text(
                        text = "o",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.grisSemaforo),
                    )
                }

                Column (
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.weight(0.6F)
                ) {
                    TextField(
                        value = latitude,
                        onValueChange = { latitude = it },
                        label = { Text("Latitud") },
                    )

                    TextField(
                        value = longitude,
                        onValueChange = { longitude = it },
                        label = { Text("Longitud") }
                    )
                }
            }


            MainButton(
                idButtonColor = R.color.grisBotones,
                text = "Cambiar ubicacion",
                onClick = { onCambiarUbicionButton(city, latitude, longitude)},
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.075F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormScreen(
        onCambiarUbicionButton = { _, _, _ -> },
        modifier = Modifier
            .fillMaxSize()
    )
}
