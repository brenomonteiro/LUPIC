package com.example.lupicapp.data.model

import com.example.lupicapp.TopBarUiState

//factory e ocp solid
class TopBarFactory {
    fun default(): TopBarUiState {
        return TopBarUiState(
            title = "",
            showTopBar = false,
            showBackArrow = true,
            showEmptyTopBar = false,
            showNavigationBar = true,
            showImageNavigationBar = true
        )
    }
}