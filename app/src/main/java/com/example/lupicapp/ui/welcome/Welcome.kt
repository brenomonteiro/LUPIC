package com.example.lupicapp.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R

@Composable
fun Welcome(navController: NavController) {
    AppScaffold(showTopBar = false) { innerPadding, _ ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logotipo_completo),
                        contentDescription = "Imagem do login",
                        modifier = Modifier.align(Alignment.Center) // Centraliza a imagem
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 84.dp),
                    text = "Boas vindas!",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 26.dp),
                    text = "Aqui inicia a sua jornada no controle e acompanhamento do Lúpus!",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
//            item {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(start = 16.dp, end = 16.dp, top = 28.dp)
//                        .border(
//                            width = 1.dp,
//                            color = colorResource(id = R.color.border),
//                            shape = RoundedCornerShape(1.dp)
//                        )
//                ) {
//                    Text(
//                        modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
//                        text = "Acessar jornal",
//                        fontSize = 16.sp,
//                        color = Color.Black,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }

            item {
                Button(
                    onClick = {
                        navController.navigate("register")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_800), // Cor de fundo do botão
                        contentColor = Color.White // Cor do texto do botão
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        // modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
                        text = "Criar conta",
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {
                Text(
                    text = buildAnnotatedString {
                        append("Já possui conta? ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Log in")
                        pop()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("login") // Redireciona para a tela home após o login
                        }, // Adiciona algum padding, se necessário
                    fontSize = 16.sp, // Define o tamanho da fonte
                    color = Color.Black, // Cor do texto
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewstokEdit() {
    // LoginCompose()
}
