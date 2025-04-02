package com.example.lupicapp.ui.journal


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.model.textWithSpan

@Composable
fun LupusType() {
    AppScaffold(showTopBar = false, showBackArrow = true) { innerPadding, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 10.dp
            ),
        ) {
            item {
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Tipos de Lúpus",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "O Lúpus pode ser manifestar de quatro formas diferentes, que têm causas distintas.\n" +
                            "As principais formas da doença são:"

                Text(
                    text = text.highlightWord(targetWord = "As principais formas da doença são:", fontSize = 16.sp ),
                    fontSize = 16.sp
                )
            }



            val treatmentMethodsList = arrayOf(
                textWithSpan("Lúpus Discoide: esse tipo de lúpus fica limitado à pele  da pessoa. Pode ser identificado com o surgimento de lesões avermelhadas com tamanhos, formatos e colorações específicas na pele, especialmente no rosto, na nuca e/ou no coro cabeludo.",
                 "Lúpus Discoide:"),
                textWithSpan("Lúpus Sistêmico: esse tipo de lúpus é o mais comum e pode ser leve ou grave, conforme cada situação. Nessa forma da doença, a  inflamação acontece em todo o organismo da pessoa, o que compromete vários órgãos ou sistemas, além da pele, como rins, coração, pulmões,  sangue e articulações. Algumas pessoas que têm o lúpus discoide podem, eventualmente, evoluir para o lúpus sistêmico.",
                    "Lúpus Sistêmico:"),
                textWithSpan("Lúpus induzido por drogas: essa forma do lúpus também é comum e acontece porque substância de algumas drogas e/ou medicamentos podem provocar inflamação com sintomas parecidos com o lúpus sistêmico. No entanto, a doença, nesse caso, tende a desaparecer assim que o uso da  substância terminar.",
                    "Lúpus induzido por drogas:"),
                textWithSpan("Lúpus neonatal: esse tipo de lúpus é bastante raro e  afeta filhos recém-nascidos de mulheres que têm lúpus. Normalmente, ao  nascer, a criança pode ter erupções na pele, problemas no fígado ou  baixa contagem de células sanguíneas, mas esses sintomas tendem a  desaparecer naturalmente após alguns meses.",
                    "Lúpus neonatal:")


            )


            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(treatmentMethodsList) { item ->
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(bottom = 24.dp)) {
                    Text("•",
                        fontSize = 30.sp,
                        color = colorResource(id = R.color.purple_800),
                        modifier = Modifier.padding(end = 8.dp)) // Marcador
                    Text(item.text.highlightWord(targetWord = item.span, fontSize = 20.sp), fontSize = 16.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
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
fun LupusTypePreview() {
    LupusType()
}
