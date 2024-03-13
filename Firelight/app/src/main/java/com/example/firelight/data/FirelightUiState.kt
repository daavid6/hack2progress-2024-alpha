package com.example.firelight.data

import androidx.annotation.StringRes
import com.example.firelight.R

data class FirelightUiState(
    @StringRes var knowledge: Int = R.string.consejo1,
    val address: String = "",
    val semaforo: String = "",
)