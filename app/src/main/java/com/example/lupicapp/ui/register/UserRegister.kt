package com.example.lupicapp.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.ui.login.LoginResult
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserRegister(
    viewModel: UserRegisterViewModel = koinViewModel(),
    navController: NavController, // Aqui está a correção
    onLoginSuccess: () -> Unit
) {
    val email by viewModel.email.collectAsState()
    val repeatEmail by viewModel.repeatEmail.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatPassword by viewModel.repeatPassword.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val repeatEmailError by viewModel.repeatEmailError.collectAsState()
    val repeatEmailRequiredField by viewModel.repeatEmailRequiredField.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val repeatPasswordError by viewModel.repeatPasswordError.collectAsState()
    val repeatPasswordRequired by viewModel.repeatPasswordRequiredField.collectAsState()
    val loginState by viewModel.loginState.collectAsState()
    val loadState by viewModel.loadState

    Box(modifier = Modifier.fillMaxSize()) {
        AppScaffold(showTopBar = false) { innerPadding, snackbarHostState ->
            LazyColumn(
                // horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ) {
                item {
                    when (val state = loginState) {
                        is LoginResult.Error -> {
                            LaunchedEffect(state) {
                                snackbarHostState.showSnackbar(
                                    message = "Erro: ${state.message}",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }

                        is LoginResult.Success -> {
                            LaunchedEffect(Unit) {
                                navController.navigate("home") {
                                    popUpTo("register") { inclusive = true }
                                    popUpTo("welcome") { inclusive = true }
                                } // Redireciona para a tela home após o login
                            }
                        }

                        else -> {}
                    }
                }

                item {
                    Text(
                        text = "Cadastre-se",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Informe seu e-mail e crie uma senha",
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
                            viewModel.onEmailChange(it)
                            // emailError = it.isBlank() // Marca erro se estiver vazio
                        },
                        placeholder = { Text(text = "Digite seu e-mail") },
                        supportingText = {
                            if (emailError) {
                                Text(
                                    text = "Campo obrigatório"
                                )
                            }
                        },
                        isError = emailError, // Aplica erro visual
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                item {
                    Text(
                        text = "Repita o e-mail",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    ) // Texto acima do campo
                    TextField(
                        value = repeatEmail ?: "",
                        onValueChange = {
                            viewModel.onRepeatEmailChange(it)
                            // emailError = it.isBlank()
                            // Marca erro se estiver vazio
                        },
                        supportingText = {
                            // if (repeatEmailError) {
                            Text(
                                text = repeatEmailRequiredField
                            )
                            //  }
                        },
                        placeholder = { Text(text = "Repita o e-mail") },
                        isError = repeatEmailError, // Aplica erro visual
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                item {
                    var passwordVisible1 by remember { mutableStateOf(false) }

                    Text(
                        text = "Crie uma senha",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password ?: "",
                        onValueChange = {
                            // password = it
                            viewModel.onPasswordChange(it)
                            // passwordError = it.isBlank()
                        },
                        placeholder = { Text(text = "Digite sua senha") },
                        isError = passwordError, // Aplica erro visual
                        singleLine = true,
                        visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon =
                                if (passwordVisible1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Alternar visibilidade da senha"
                                )
                            }
                        },
                        supportingText = {
                            if (passwordError) {
                                Text(
                                    text = "Campo obrigatório"
                                )
                            }
                        },
                    )
                }

                item {
                    var passwordVisible by remember { mutableStateOf(false) }

                    Text(
                        text = "Repita sua senha",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = repeatPassword ?: "",
                        onValueChange = {
                            viewModel.onRepeatPasswordChange(it)
                        },
                        placeholder = { Text(text = "Digite sua senha") },
                        isError = repeatPasswordError, // Aplica erro visual
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
                        },
                        supportingText = {
                            Text(
                                text = repeatPasswordRequired
                            )
                        },
                    )
                }

                item {
                    Button(
                        onClick = {
                            viewModel.registerUser()
                            // viewModel.registerUser()
                        },
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.purple_800)
                        ), // Fundo branco
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        Text(text = "Cadastrar", color = Color.White)
                    }
                }
            }
        }
    }
    if (loadState is LoginResult.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)), // Escurece a tela
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}
