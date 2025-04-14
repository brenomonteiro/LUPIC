package com.example.lupicapp.ui.jetpackComponents

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lupicapp.R

@Composable
fun CustomTextField(modifier: Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        label = { Text("Pesquisar") },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.add_roxo),
                contentDescription = "Ícone de pesquisa",
                modifier = Modifier.size(24.dp) // Ajusta o tamanho do ícone
            )
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,   // Fundo branco quando focado
            unfocusedContainerColor = Color.White, // Fundo branco quando não focado
            disabledContainerColor = Color.White,  // Fundo branco quando desativado
        ),
    )
}