package com.example.lupicapp.ui.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.TopBarUiState
import com.example.lupicapp.UiStateViewModel
import com.example.lupicapp.composeComponents.CheckboxAndTextRow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Login(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController, // Aqui está a correção
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }


    val uiStateViewModel: UiStateViewModel = getViewModel()
    LaunchedEffect(Unit) {
        uiStateViewModel.setTopBar(
            title = "",
            showTopBar = false,
            showEmptyTopBar = false,
            showBackArrow = true,
            showImageNavigationBar = false
        )
    }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.signInWithGoogle(result.data) { success ->
                    if (success) {
                        onLoginSuccess()
                    } else {
                        Log.i("erro login", "erroooo")
                    }
                }
            }
        }




    Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                // horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Acesse",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Com e-mail e senha para entrar",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                item {
                    Text(
                        text = "E-mail",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 70.dp)
                    ) // Texto acima do campo
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = it.isBlank() // Marca erro se estiver vazio
                        },
                        placeholder = { Text(text = "Digite seu e-mail") },
                        supportingText = {
                            if (emailError) {
                                Text(
                                    text = "Campo obrigatório"
                                )
                            }
                        },
//                        label = {
//                            Text(
//                                text = "Email",
//                                color = if (emailError) Color.Red else Color.Gray
//                            )
//                        },
                        isError = emailError, // Aplica erro visual
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                item {
                    var passwordVisible by remember { mutableStateOf(false) }

                    Text(
                        text = "Senha",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = it.isBlank()
                        },
                        supportingText = {
                            if (passwordError) {
                                Text(
                                    text = "Campo obrigatório"
                                )
                            }
                        },
                        placeholder = { Text(text = "Digite sua senha") },
                        isError = passwordError, // Aplica erro visual
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon =
                                if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Alternar visibilidade da senha"
                                )
                            }
                        }
                    )
                }

                item {
                    // /sugestão, ao invez de passar o navcontroler, retornar um lambda e tratar o navigate direto nessa tela
                    CheckboxAndTextRow(navController)
                }

                item {
                    Button(
                        onClick = {
                            emailError = email.isBlank()
                            passwordError = password.isBlank()

                            if (!emailError && !passwordError) {
                                viewModel.login(email, password)
                            }
                        },
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.purple_800)
                        ), // Fundo branco
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        Text(text = "Acessar", color = Color.White)
                    }

                    when (val state = loginState) {
                        is LoginResult.Error -> {
//                            LaunchedEffect(state) {
//                                snackbarHostState.showSnackbar(
//                                    message = "Erro: ${state.message}",
//                                    duration = SnackbarDuration.Short
//                                )
//                            }
                        }

                        is LoginResult.Success -> {
                            LaunchedEffect(Unit) {
                                navController.navigate("home") // Redireciona para a tela home após o login
                            }
                        }

                        else -> {}
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier
                                .width(60.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "Ou continue com",
                            fontSize = 12.sp,
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier
                                .width(60.dp)
                                .padding(start = 8.dp)
                        )
                    }
                }

                item {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 28.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(58.dp)
                                .background(colorResource(id = R.color.white_400))
                                .clickable {
                                    viewModel.viewModelScope.launch {
                                        val intentSender = viewModel.getSignInIntent()
                                        intentSender?.let {
                                            launcher.launch(
                                                IntentSenderRequest
                                                    .Builder(it)
                                                    .build()
                                            )
                                        }
                                    }
                                } // Tamanho do conteúdo
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = "ícone google",
                                // modifier = Modifier.size(30.dp)
                            )
                        }
//                        when (loginState) {
//                            is LoginResult.Success -> onLoginSuccess()
//                            //is LoginResult.Error -> Text(text = "Erro: ${(loginState as LoginResult.Error).message}")
//                            else -> {}
//                        }
                        Spacer(modifier = Modifier.width(28.dp))

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(58.dp)
                                .background(colorResource(id = R.color.white_400))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = "Imagem à esquerda",
                                // modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

    }

    when (loginState) {
        LoginResult.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)), // Escurece a tela
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        is LoginResult.Success -> onLoginSuccess()
        else -> {}
    }
}
