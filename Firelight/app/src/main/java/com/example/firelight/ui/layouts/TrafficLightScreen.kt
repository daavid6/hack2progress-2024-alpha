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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
                idColor = R.color.grisBotones,
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




/*
 Surface(
        color = colorResource(R.color.grisFondo),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp, 0.dp, 32.dp, 0.dp)

        ) {


            Box (
                modifier= Modifier.weight(0.65F)
            ){

                Box(modifier = modifier
                    .size(130.dp)
                    .align(Alignment.TopStart)
                    .offset(y = (40).dp)
                ){
                    Text(
                        text = address,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                // Icono
                Image(
                    painter = painterResource(id = R.drawable.firealert),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.grisSemaforo)),
                    modifier = modifier
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                        .offset(y = (24).dp)
                        .clickable(onClick = onAlertButton)
                )

                //Palo
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(R.drawable.palo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(800.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = (-100).dp)
                )

                //Semaforo
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(R.drawable.semaforoon),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (100).dp)
                )
            }


            Column (
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .weight(0.35F)
            ) {
                Spacer(
                    modifier.weight(0.55F)
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

                Spacer(
                    modifier.weight(0.15F)
                )
            }
        }
    }
 */