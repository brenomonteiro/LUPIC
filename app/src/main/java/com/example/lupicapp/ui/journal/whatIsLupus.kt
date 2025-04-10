package com.example.lupicapp.ui.journal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R

@Composable
fun Lupus(
    navController: NavController
) {
    AppScaffold(navController = navController, showBackArrow = true) { innerPadding, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()+10.dp
            ),
        ) {
            item {
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "O que é Lúpus?",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "Lúpus é uma doença inflamatória autoimune, que pode afetar múltiplos órgãos e tecidos, como pele, articulações, rins e cérebro. Em casos mais graves, se não tratada adequadamente, pode matar. O nome  científico da doença é \"Lúpus Eritematoso Sistêmico (LES)\"."

                Text(
                    text = text.highlightWord(
                        "Lúpus",
                        color = colorResource(id = R.color.purple_800),
                        isBold = true
                    ),
                    fontSize = 16.sp
                )
            }

            item {
                val text =
                    "Doenças autoimunes são aquelas em que o sistema imunológico da pessoa ataca tecidos saudáveis do próprio corpo, por engano. As causas das doenças autoimunes ainda não são conhecidas. A teoria mais aceita é que fatores externos estejam envolvidos na ocorrência dessa condição, principalmente quando há predisposição genética e o uso de alguns medicamentos. A maioria das doenças autoimunes são crônicas, ou seja, não são transmissíveis, no entanto muitas delas podem ser  controladas com tratamento. Além disso, os sintomas das doenças autoimunes podem aparecer e desaparecer continuamente, sem causa aparente."
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = text.highlightWord(
                        "Doenças autoimunes",
                        color = colorResource(id = R.color.purple_800),
                        isBold = true
                    ),
                    fontSize = 16.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment =  Alignment.CenterEnd) {
                    Text(
                        text = "Fonte: Ministério da Saúde",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.purple_800)
                    )
                }
            }


        }

    }
}

@Preview
@Composable
fun lupusPreview() {
  //  Lupus()
}


fun String.highlightWord(
    targetWord: String,
    color: Color = Color(0xFF800080), // Roxo padrão
    isBold: Boolean = false,
    fontSize: TextUnit = 20.sp // Define se será negrito
) = buildAnnotatedString {
    val startIndex = indexOf(targetWord)

    if (startIndex != -1) {
        append(substring(0, startIndex)) // Parte antes da palavra-alvo

        withStyle(
            style = SpanStyle(
                color = color,
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                fontSize = fontSize
            )
        ) {
            append(targetWord) // Palavra destacada
        }

        append(substring(startIndex + targetWord.length)) // Parte depois da palavra-alvo
    } else {
        append(this@highlightWord) // Caso a palavra não exista, exibe o texto normal
    }
}