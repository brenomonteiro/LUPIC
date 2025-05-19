package com.example.lupicapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController? = null,
    showTopBar: Boolean = false,
    showBackArrow: Boolean = false, // Controla se a seta aparece
    onBackClick: () -> Unit = {},
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
                    title = {},
                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.logosplash),
                            contentDescription = "Imagem à esquerda",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(end = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(22.dp))
                    },
                    navigationIcon = {
                        if (showBackArrow) {
                            IconButton(onClick = { navController?.popBackStack() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow),
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController?.navigate("home") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    icon = { Icon(Icons.Default.Home, "") },
                    label = { Text("Home") }
                )
            }
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        colorResource(id = R.color.white_400),
//                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
//                    ) // Arredondando os cantos superiores
//                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
//                    .border(
//                        width = 1.dp, // Espessura da borda
//                        color = colorResource(R.color.border), // Cor da borda
//                        shape = RoundedCornerShape(
//                            topStart = 24.dp,
//                            topEnd = 24.dp
//                        ) // Mesma forma para alinhar a borda
//                    )
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 40.dp, end = 40.dp, top = 16.dp, bottom = 16.dp)
//                        .background(
//                            colorResource(id = R.color.white_400)
//                        ),
//
//                    horizontalArrangement = Arrangement.SpaceBetween) {
//                    Image(
//                        painter = painterResource(id = R.drawable.home),
//                        contentDescription = "Imagem à esquerda",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.calendario),
//                        contentDescription = "Imagem à esquerda",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.chat),
//                        contentDescription = "Imagem à esquerda",
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//            }
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
fun TopBarWithImageAndText(
    // showBackArrow: Boolean,
    // onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                if (showBackArrow) {
//                    Image(
//                        painter = painterResource(id = R.drawable.arrow), // Ícone da seta
//                        contentDescription = "Voltar",
//                        modifier = Modifier
//                            .size(24.dp)
//                            .clickable { onBackClick() }
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                }

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
        actions = {
            Image(
                painter = painterResource(id = R.drawable.sino),
                contentDescription = "Imagem à esquerda",
                // modifier = Modifier.size(40.dp)
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
