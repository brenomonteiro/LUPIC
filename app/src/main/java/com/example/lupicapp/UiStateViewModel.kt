package com.example.lupicapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UiStateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        TopBarUiState(
            title = "Bem-vindo",
            showTopBar = true,
            showBackArrow = false
        )
    )
    val uiState: StateFlow<TopBarUiState> = _uiState.asStateFlow()

    fun setTopBar(title: String, showTopBar: Boolean = true, showBackArrow: Boolean = false) {
        _uiState.value = TopBarUiState(title, showTopBar, showBackArrow)
    }
}

data class TopBarUiState(
    val title: String,
    val showTopBar: Boolean,
    val showBackArrow: Boolean
)
