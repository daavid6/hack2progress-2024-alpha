package com.example.firelight.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainButton(
    @ColorRes idColor: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 60.dp,
    width: Dp = 300.dp,
    fontSize: TextUnit = 20.sp
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = idColor)
        ),
        onClick = onClick,
        modifier = modifier
            .height(height)
            .width(width)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}