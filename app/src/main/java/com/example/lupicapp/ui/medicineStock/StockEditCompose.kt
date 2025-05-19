import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.ui.medicineStock.StockEditViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StokEdit(
    navController: NavController,
    id: String,
    viewModel: StockEditViewModel = koinViewModel()
) {
    val context = LocalContext.current

    var totalPills by remember { mutableStateOf("") }
    var pillsADay by remember { mutableStateOf("") }
    var timesADay by remember { mutableStateOf("") }
    var firstPeriod by remember { mutableStateOf("") }
    var secondPeriod by remember { mutableStateOf("") }

    val medicamento = viewModel.medicamento
    LaunchedEffect(medicamento) {
        viewModel.carregarMedicamento(id)
        medicamento?.let {
            totalPills = it.totalPills
            pillsADay = it.pillsADay
            timesADay = it.timesADay
            firstPeriod = it.firstPeriod
            secondPeriod = it.secondPeriod
        }
    }

    AppScaffold(navController = navController, showBackArrow = true) { innerPadding, _ ->
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
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = medicamento?.name ?: "",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.purple_100) // cor de fundo do Card
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.caixa_estoque),
                                contentDescription = "Imagem à esquerda",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "Estoque",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = totalPills ?: "",
                            onValueChange = { totalPills = it },
                            label = { Text("Comprimidos") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    Modifier
                        .background(
                            colorResource(id = R.color.purple_100),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = "Posologia",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = pillsADay ?: "",
                        onValueChange = { pillsADay = it },
                        label = { Text("Comprimidos por dose") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = timesADay ?: "",
                        onValueChange = { timesADay = it },
                        label = { Text("Vezes ao dia") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "1ª dose")
                        Spacer(modifier = Modifier.width(118.dp))
                        OutlinedTextField(
                            value = firstPeriod ?: "",
                            onValueChange = { firstPeriod = it },
                            label = { Text("Horário") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "2ª dose")
                        Spacer(modifier = Modifier.width(118.dp))
                        OutlinedTextField(
                            value = secondPeriod ?: "",
                            onValueChange = { secondPeriod = it },
                            label = { Text("Horário") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
            item {
                Button(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    onClick = {
                        medicamento?.let {
                            it.totalPills = totalPills
                            it.pillsADay = pillsADay
                            it.timesADay = timesADay
                            it.firstPeriod = firstPeriod
                            it.secondPeriod = secondPeriod

                            viewModel.editarMedicamento(id) { sucesso ->
                                if (sucesso) {
                                    Toast.makeText(
                                        context,
                                        "Medicamento atualizado com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(context, "Erro ao atualizar medicamento.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_800))
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewstokEdit() {
    // StokEdit()
}
