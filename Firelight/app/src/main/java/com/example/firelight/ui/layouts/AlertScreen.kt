package com.example.firelight.ui.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firelight.R


@Composable
fun AlertScreen(
    modifier: Modifier = Modifier,
    on112ButtonClicked: () -> Unit,
) {
    Surface(
        color = colorResource(R.color.rojoFondo),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.lightalert),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.grisSemaforo)),
                alignment = Alignment.BottomCenter,
                modifier = Modifier
                    .size(150.dp)
                    .weight(0.4F)


            )
            Text(
                text = "¿Estás seguro?\n¿Quieres llamar al 112?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(0.3F)
            )

            Box (
                modifier = modifier
                    .weight(0.3F)
            ){
                Button(
                    onClick = on112ButtonClicked,
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier
                        .height(100.dp)
                        .width(300.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.telefono),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.grisSemaforo)),
                            modifier = modifier.size(50.dp)
                        )

                        Text(
                            text= "112",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlertScreenPreview() {
    AlertScreen(
        on112ButtonClicked = {}
    )
}