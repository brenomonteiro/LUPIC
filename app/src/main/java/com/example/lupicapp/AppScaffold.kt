package com.example.lupicapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    showTopBar: Boolean = false,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }, // Estado da Snackbar
    content: @Composable (PaddingValues, SnackbarHostState) -> Unit,
) {
    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBarWithImageAndText() // Exibe a TopBar normalmente
            } else {
                // Quando showTopBar for false, exibe um TopAppBar vazio (sem conteúdo, mas ainda ocupando o espaço)
                TopAppBar(
                    title = {}
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            }
        }
    ) { innerPadding ->
        content(innerPadding, snackbarHostState) // Passa o innerPadding para o conteúdo
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithImageAndText() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logosplash),
                    contentDescription = "Imagem à esquerda",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )

                Text(
                    text = "Olá!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
        },
        actions ={
            Image(
                painter = painterResource(id = R.drawable.sino),
                contentDescription = "Imagem à esquerda",
                //modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Image(
                painter = painterResource(id = R.drawable.sandwiche),
                contentDescription = "Imagem à esquerda",
               // modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(22.dp))

        }
    )
}
