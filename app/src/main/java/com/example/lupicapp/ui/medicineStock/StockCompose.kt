import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
//import com.example.lupic.R
import com.example.lupicapp.R
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.ui.medicineStock.StockViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Stok(
    navController: NavController,
    viewModel: StockViewModel = koinViewModel()
) {
    val medicamentos = viewModel.medicamentos
    LaunchedEffect(Unit) {
        viewModel.carregarMedicamentos()
    }

    AppScaffold(navController = navController,showBackArrow = true){ innerPadding, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), // Fundo branco para garantir a separação
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            item {
                Column(
                    modifier = Modifier
                        //.fillMaxSize()
                        .background(
                            colorResource(id = R.color.purple_100),
                            shape = RoundedCornerShape(12.dp)
                        )
                    //.padding(start = 34.dp, end = 16.dp)
                ) {
                    Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.caixa_estoque),
                                contentDescription = "Imagem à esquerda",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "Estoque de Medicamentos",
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.purple_800),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Lista de medicamentos
                        medicamentos.forEach {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(
                                        color = colorResource(id = R.color.formulario),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                            ) {
                                Column(
                                    Modifier
                                        .padding(16.dp)
                                        .fillMaxSize()
                                ) {
                                    // Nome do medicamento

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        Text(
                                            text = it.name,
                                            fontSize = 20.sp,
                                            color = colorResource(id = R.color.purple_800),
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Spacer(modifier = Modifier.weight(1f))

                                        Image(
                                            modifier = Modifier.clickable {
                                                navController.navigate("editar_medicamento/${it.id}")
                                            },
                                            painter = painterResource(id = R.drawable.caneta_editar),
                                            contentDescription = "Imagem à esquerda",
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.lixeira),
                                            contentDescription = "Imagem à esquerda",
                                        )

                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    // Informações do medicamento

                                    Row {
                                        Image(
                                            painter = painterResource(id = R.drawable.comprimido),
                                            contentDescription = "Imagem à esquerda",
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "${it.pillsADay} comprimidos por dia",
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Row {
                                        Image(
                                            painter = painterResource(id = R.drawable.caixa_estoque),
                                            contentDescription = "Imagem à esquerda",
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "${it.totalPills} comprimidos",
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )
                                    }

                                }
                            }
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .clickable { navController.navigate("AddPill") }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Imagem à esquerda",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Adicionar medicamento", fontSize = 12.sp)
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewstok() {
   // Stok()
}