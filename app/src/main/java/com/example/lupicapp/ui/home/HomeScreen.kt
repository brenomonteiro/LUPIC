package com.example.lupicapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.model.GridItem

val items = arrayOf(
    GridItem("Medicamentos", R.drawable.medicamentos, "medicamentos"),
    GridItem("Sintomas", R.drawable.sintomas, "sintomas"),
    GridItem("Jornal", R.drawable.jornal, "jornal")
)

@Composable
fun HomeScreen( navController: NavController) {
    AppScaffold(showTopBar = true) { innerPadding, _ ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Define 2 colunas fixas
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ) {

            items(items) { item -> // 10 itens na grid
                Box(
                    modifier = Modifier
                        .aspectRatio(1f) // Mantém o quadrado
                        .background(
                            colorResource(id = R.color.purple_800),
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { navController.navigate(item.navigationRoute) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = "Imagem à esquerda",
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = item.title, color = Color.White, fontSize = 18.sp)

                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SimpleGridPreview() {
   // HomeScreen()
}


