package com.example.firelight.ui.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.firelight.R
import com.example.firelight.ui.components.MainButton

@Composable
fun TrafficLightScreen(
    address: String,
    modifier: Modifier = Modifier,
    onCambiarUbicionButton: () -> Unit,
    onAlertButton: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(32.dp,32.dp, 32.dp, 48.dp)
    ) {

        Row (
            modifier = Modifier
                .weight(0.10F)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.10F)
            ) {
                Text(
                    text = address,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.grisSemaforo),
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.firealert),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.grisSemaforo)),
                    modifier = Modifier
                        .size(50.dp)
                        .clickable(onClick = onAlertButton)
                )
            }
        }

        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.75F)
                .zIndex(-1f) // Esto enviará el Box al fondo
        ) {
            //Palo
            Image(
                alignment = Alignment.Center,
                painter = painterResource(R.drawable.palo),
                contentDescription = null,
                modifier = Modifier
                    .size(800.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-200).dp)
            )

            //Semaforo
            Image(
                alignment = Alignment.Center,
                painter = painterResource(R.drawable.semaforoon),
                contentDescription = null,
                contentScale = ContentScale.Inside
            )


        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.15F)
        ) {
            Spacer(modifier = Modifier.weight(0.5F))

            MainButton(
                idButtonColor = R.color.grisBotones,
                text = "Cambiar ubicacion",
                onClick = onCambiarUbicionButton,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5F)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrafficLightScreenPreview() {
    TrafficLightScreen(
        address = "Santander",
        onCambiarUbicionButton = {},
        onAlertButton = {},
        modifier = Modifier
            .fillMaxSize()
    )
}