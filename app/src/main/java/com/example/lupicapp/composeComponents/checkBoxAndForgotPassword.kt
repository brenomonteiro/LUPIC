package com.example.lupicapp.composeComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CheckboxAndTextRow(navController: NavController) {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )

        Text(
            text = "Lembrar de mim",
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.weight(1f)) // Empurra o próximo texto para a direita

        Text(
            modifier = Modifier.clickable {
                navController.navigate("register") // Redireciona para a tela home após o login
            },
            text = "Esqueci minha senha",
            fontSize = 12.sp
        )
    }
}
