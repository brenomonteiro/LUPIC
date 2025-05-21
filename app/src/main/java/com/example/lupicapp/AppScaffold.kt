package com.example.lupicapp

import android.util.Log
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lupicapp.composeComponents.snackbar.ObserveAsEvent
import com.example.lupicapp.composeComponents.snackbar.SnackbarController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController? = null,
    uiStateViewModel: UiStateViewModel, onBackClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val uiState by uiStateViewModel.uiState.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    ObserveAsEvent(
        flow = SnackbarController.events,
        snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
    Scaffold(
        topBar = {
            if (uiState.showTopBar) {
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
                        if (uiState.showBackArrow) {
                            Log.i("aooooooooooo", uiState.showBackArrow.toString())
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
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        content(innerPadding) // Passa o innerPadding para o conteúdo
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
            )
            Spacer(modifier = Modifier.width(22.dp))
        }
    )
}
