package com.example.lupicapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UiStateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        TopBarUiState()
    )
    val uiState: StateFlow<TopBarUiState> = _uiState.asStateFlow()

    fun setTopBar(
        title: String,
        showTopBar: Boolean = true,
        showEmptyTopBar: Boolean = false,
        showNavigationBar: Boolean = false,
        showBackArrow: Boolean = false,
        showImageNavigationBar: Boolean = true
    ) {
        _uiState.value = TopBarUiState(
            title,
            showTopBar,
            showBackArrow,
            showEmptyTopBar,
            showNavigationBar,
            showImageNavigationBar
        )
    }


    fun setTopBar(
        state: TopBarUiState
    ) {
        _uiState.value = state
    }
}

data class TopBarUiState(
    val title: String = "-",
    val showTopBar: Boolean = false,
    val showBackArrow: Boolean = false,
    val showEmptyTopBar: Boolean = false,
    val showNavigationBar: Boolean = false,
    val showImageNavigationBar: Boolean = false
)
