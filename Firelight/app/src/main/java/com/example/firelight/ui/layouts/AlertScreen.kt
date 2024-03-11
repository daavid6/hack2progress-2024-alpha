package com.example.firelight.ui.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firelight.R
import com.example.firelight.ui.components.MainButton


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
                    .padding(bottom = 10.dp)
            )

            Text(
                text = "¿Estás seguro?\n¿Quieres llamar al 112?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(200.dp))

            MainButton(
                idButtonColor = R.color.rojoBotonEmergencia,
                text = "112",
                onClick = on112ButtonClicked,
                buttonHeight = 80.dp,
                idIcon = R.drawable.telefono,
                idIconColor = R.color.White,
                fontSize = 30.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlertScreenPreview() {
    AlertScreen(
        Modifier.fillMaxSize(),
        on112ButtonClicked = {}
    )
}
