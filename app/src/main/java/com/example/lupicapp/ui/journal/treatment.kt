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
fun Treatment(
    navController: NavController
) {
    AppScaffold(navController = navController, showBackArrow = true) { innerPadding, _ ->
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
                    text = "Tratamento",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "O tratamento do Lúpus, assim como para outras  doenças crônicas como câncer, hipertensão e diabetes, é mais paliativo e  tem por objetivo controlar os sintomas, melhorando a qualidade de vida  da pessoa. Isso porque, até o momento, Lúpus não tem cura.\n" +
                        "O tratamento é diferenciado para cada caso, conforme os níveis de intensidade e agressividade da doença."

                Text(
                    text = text.highlightWord(
                        "O tratamento é diferenciado para cada caso",
                        color = colorResource(id = R.color.purple_800),
                        isBold = false,
                        fontSize = 16.sp
                    ),
                    fontSize = 16.sp
                )
            }

            item {
                val text =
                    "Lúpus leve pode ser tratado com:"
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = text,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_800),
                    fontWeight = FontWeight.Bold
                )
            }

            val treatmentMethodsList = arrayOf(
                "Anti-inflamatórios não esteroides para artrite e pleurisia.",
                "Protetor solar para as lesões de pele.",
                "Corticoide tópico para pequenas lesões na pele.",
                "Droga antimalárica (hidroxicloroquina).",
                "Corticoides de baixa dosagem para os sintomas de pele e artrite."
            )

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(treatmentMethodsList) { item ->
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(0.dp)) {
                    Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp)) // Marcador
                    Text(item, fontSize = 18.sp)
                }
            }

            item {
                val text =
                    "Lúpus graves podem ser tratados com:"
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = text,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_800),
                    fontWeight = FontWeight.Bold
                )
            }

            val advancedTreatmentMethodsList = arrayOf(
                "Alta dosagem de corticoides ou medicamentos para diminuir a resposta do sistema imunológico do corpo (imunossupressores).",
                "Drogas  citotóxicas (drogas que bloqueiam o crescimento celular), quando não  houver melhora com corticoides ou quando os sintomas piorarem depois de  interromper o uso."
            )

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(advancedTreatmentMethodsList) { item ->
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(0.dp)) {
                    Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp)) // Marcador
                    Text(item, fontSize = 18.sp)
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
}

@Preview
@Composable
fun treatmentPreview() {
    // Treatment()
}
