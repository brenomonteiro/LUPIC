package com.example.lupicapp.model

data class GridItem(
    val title: String,
    val imageRes: Int, // ID da imagem (drawable)
    val action: () -> Unit // Função que representa a ação ao clicar
)
