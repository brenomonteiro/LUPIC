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
fun PatientJourney(
    navController: NavController
) {
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
                    text = "Jornada do paciente",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))

                val text =
                    "A jornada do paciente com uma doença reumática autoimune passa por diversas etapas, desde a percepção dos primeiros sintomas, passando pela decisão de marcar consulta com um médico."

                Text(
                    text = text,
                    fontSize = 16.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "O processo para chegar a um médico reumatologista, receber o diagnóstico correto e, assim, ter a prescrição do tratamento adequado em muitos casos é demorado. São muitos os desafios que podem estar presentes, como idas a diferentes médicos, diversas vezes, receber diagnósticos errados, falta de acesso ao reumatologista, além das dificuldades de realizar os exames necessários para o diagnóstico e ao tratamento",
                    fontSize = 16.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "A forma com que o paciente passa por essa experiência durante sua jornada é fundamental para os desfechos da sua doença. Por isso, encurtá-la é uma das formas mais eficazes para a melhoria da qualidade de vida.",
                    fontSize = 16.sp
                )
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
fun PatientJourneyPreview() {
    // PatientJourney()
}
