package com.example.firelight.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.firelight.R
import com.example.firelight.data.FirelightUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirelightViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FirelightUiState())
    val uiState: StateFlow<FirelightUiState> = _uiState.asStateFlow()

    fun randomKnowledge(): Int {
        val num = 1..9
        val randomNumber = num.random()

        @StringRes val knowledge: Int = when (randomNumber) {
            1 -> R.string.consejo1
            2 -> R.string.consejo2
            3 -> R.string.consejo3
            4 -> R.string.consejo4
            5 -> R.string.consejo5
            6 -> R.string.consejo6
            7 -> R.string.consejo7
            8 -> R.string.consejo8
            9 -> R.string.consejo9
            else -> R.string.consejo10
        }

        _uiState.value = _uiState.value.copy(knowledge = knowledge)

        return randomNumber
    }
}