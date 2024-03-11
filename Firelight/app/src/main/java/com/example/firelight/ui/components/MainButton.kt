package com.example.firelight.ui.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firelight.R

@Composable
fun MainButton(
    @ColorRes idButtonColor: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonHeight: Dp = 60.dp,
    buttonWidth: Dp = 300.dp,
    fontSize: TextUnit = 20.sp,
    @DrawableRes idIcon: Int? = null,
    @ColorRes idIconColor: Int = R.color.grisSemaforo
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = idButtonColor)
        ),
        onClick = onClick,
        modifier = modifier
            .height(buttonHeight)
            .width(buttonWidth)
    ) {
        if (idIcon != null) {
            Icon(
                painter = painterResource(idIcon),
                contentDescription = null,
                tint = colorResource(id = idIconColor),
                modifier = Modifier
                    .height(fontSize.value.dp)
                    .width(fontSize.value.dp)
            )
        }

        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}