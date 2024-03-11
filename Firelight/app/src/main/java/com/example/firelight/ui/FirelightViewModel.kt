package com.example.firelight.ui

import androidx.lifecycle.ViewModel
import com.example.firelight.data.FirelightUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirelightViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FirelightUiState())
    val uiState: StateFlow<FirelightUiState> = _uiState.asStateFlow()
}