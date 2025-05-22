import android.util.Log
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.lupicapp.R
import com.example.lupicapp.UiStateViewModel
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.model.TopBarFactory
import com.example.lupicapp.ui.medicineStock.AddPillViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddPill(
    navController: NavController?,
    viewModel: AddPillViewModel = koinViewModel()
) {
    val nome = remember {
        mutableStateOf("")
    }
    val total = remember {
        mutableStateOf("")
    }
    val comprimidosPorDose = remember {
        mutableStateOf("")
    }
    val vezesAoDia = remember {
        mutableStateOf("")
    }
    val firstDose = remember {
        mutableStateOf("")
    }
    val secondDose = remember {
        mutableStateOf("")
    }

    val uiStateViewModel: UiStateViewModel = getViewModel()
    LaunchedEffect(Unit) {
        uiStateViewModel.setTopBar(
            TopBarFactory().default()
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 16.dp
        ),

        ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 16.dp, top = 28.dp),
                text = "Adicionar Medicamento",
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
                        // modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.medicine),
                            contentDescription = "Imagem à esquerda",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Medicamento",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = nome.value,
                        onValueChange = { nome.value = it },
                        label = { Text("Nome do medicamento") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.height(28.dp)) }

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
                            painter = painterResource(id = R.drawable.medicine),
                            contentDescription = "Imagem à esquerda",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Posologia",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = comprimidosPorDose.value,
                        onValueChange = { comprimidosPorDose.value = it },
                        label = { Text("Comprimidos por dose") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = vezesAoDia.value,
                        onValueChange = { vezesAoDia.value = it },
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
                            value = firstDose.value,
                            onValueChange = { firstDose.value = it },
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
                            value = secondDose.value,
                            onValueChange = { secondDose.value = it },
                            label = { Text("Horário") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(28.dp)) }

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
                        value = total.value,
                        onValueChange = { total.value = it },
                        label = { Text("Comprimidos") },
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
                    viewModel.salvarMedicamento(
                        DrugItem(
                            name = nome.value,
                            pillsADay = comprimidosPorDose.value,
                            timesADay = vezesAoDia.value,
                            firstPeriod = firstDose.value,
                            secondPeriod = secondDose.value,
                            totalPills = total.value
                        )
                    ) {
                        navController?.popBackStack()
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

@Preview(showBackground = true)
@Composable
fun previewPill() {
    // AddPill(null)
}

@Preview(showBackground = true)
@Composable
fun previewCard() {
    // ElevatedCardExample()
}

// .fillMaxSize()
// .background(
// colorResource(id = R.color.purple_100),
// shape = RoundedCornerShape(12.dp)
// )
// .padding(16.dp)

@Composable
fun ElevatedCardExample(value: String) {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownHorario() {
    val horarios = listOf("08:00", "10:00", "14:00", "16:00")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Horário") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            horarios.forEach { horario ->
                DropdownMenuItem(
                    text = { Text(horario) },
                    onClick = {
                        selectedText = horario
                        expanded = false
                    }
                )
            }
        }
    }
}
