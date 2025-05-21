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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R

@Composable
fun Prognostic(
    navController: NavController
) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp+ 10.dp
            ),
        ) {
            item {
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Sintomas",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "Os sintomas do lúpus podem surgir de repente ou se desenvolver  lentamente. Eles também podem ser moderados ou graves, temporários ou  permanentes. A maioria dos pacientes com lúpus apresenta sintomas  moderados, que surgem esporadicamente, em crises, nas quais os sintomas  se agravam por um tempo e depois desaparecem."

                Text(
                    text = text,
                    fontSize = 16.sp
                )
            }

            item {
                val text =
                    "Os sintomas podem também variar de acordo com as partes do seu corpo que forem afetadas pelo lúpus. Os sinais mais comuns são:"
                Text(
                    text = text.highlightWord(
                        targetWord = "Os sinais mais comuns são:",
                        fontSize = 16.sp
                    ),
                    fontSize = 16.sp
                )
            }

            val treatmentMethodsList = arrayOf(
                "Fadiga.",
                "Febre.",
                "Dor nas articulações.",
                "Rash  cutâneo - vermelhidão na face em forma de \"borboleta\" sobre as  bochechas e a ponta do nariz. Afeta cerca de metade das pessoas com  lúpus. O rash piora com a luz do sol e também pode ser generalizado.",
                "Lesões na pele que surgem ou pioram quando expostas ao sol.",
                "Dificuldade para respirar.",
                "Dor no peito ao inspirar profundamente.",
                "Sensibilidade à luz do sol.",
                "Dor de cabeça,confusão mental e perda de memória.",
                "Linfonodos aumentados.",
                "Queda de cabelo.",
                "Feridas na boca.",
                "Desconforto geral, ansiedade, mal-estar."

            )

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(treatmentMethodsList) { item ->
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(0.dp)) {
                    Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp)) // Marcador
                    Text(item, fontSize = 16.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Sintomas Específicos:",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_800),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Outros sintomas do lúpus, mais específicos, dependem de qual é a parte do corpo afetada:",
                    fontSize = 16.sp,

                )
            }

            val advancedPrognosticList = arrayOf(
                "Cérebro e sistema nervoso: cefaleia, dormência, formigamento,  convulsões, problemas de visão, alterações de personalidade, psicose  lúpica.",
                "Trato digestivo: dor abdominal, náuseas e vômito.",
                "Coração: ritmo cardíaco anormal (arritmia).",
                "Pulmão: tosse com sangue e dificuldade para respirar.",
                "Pele: coloração irregular da pele, dedos que mudam de cor com o frio (fenômeno de Raynaud).",
                "Alguns pacientes têm apenas sintomas de pele. Esse tipo é chamado de lúpus discoide."
            )

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(advancedPrognosticList) { item ->
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(0.dp)) {
                    Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp)) // Marcador
                    Text(item, fontSize = 16.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
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

@Preview
@Composable
fun PrognosticPreview() {
    // Prognostic()
}
