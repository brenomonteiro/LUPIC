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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.lupicapp.UiStateViewModel
import com.example.lupicapp.data.model.TopBarFactory
import org.koin.androidx.compose.getViewModel

@Composable
fun Diagnostic(navController: NavController,) {
    val uiStateViewModel: UiStateViewModel = getViewModel()
    LaunchedEffect(Unit) {
        uiStateViewModel.setTopBar(
           TopBarFactory().default()
        )
    }


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp + 10.dp
            ),
        ) {
            item {
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Diagnóstico",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "O diagnóstico para Lúpus não é tão simples, porque os sintomas podem  variar muito de pessoa para pessoa e mudam com o passar do tempo, o que  em muitas vezes confunde com os sinais de outras doenças. Por isso,  ainda não há nenhum exame ou teste específico para diagnosticar o lúpus,  mas isso pode ser feito com segurança a partir de exames de sangue,  urina e dos sintomas clínicos apresentados ao médico durante exame  físico."

                Text(
                    text = text,
                    fontSize = 16.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                val text =
                    "Os exames para mais comuns e úteis para diagnosticar Lúpus são:"
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.purple_800)
                )
            }

            val treatmentMethodsList = arrayOf(
                "Exame físico.",
                "Exames de anticorpos, incluindo teste de anticorpos antinucleares.",
                "Hemograma completo.",
                "Radiografia do tórax.",
                "Biópsia renal.",
                "Exame de urina."

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
fun DiagnosticPreview() {
    // Diagnostic()
}
