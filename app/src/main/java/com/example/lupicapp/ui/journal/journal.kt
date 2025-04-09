package com.example.lupicapp.ui.journal

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.size.Size
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.model.JornalItem
import com.example.lupicapp.ui.home.HomeScreen
import com.example.lupicapp.ui.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Journal(
//    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController,// Aqui está a correção
//    onLoginSuccess: () -> Unit
) {

    AppScaffold(showTopBar = true) { innerPadding, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            ),
        ) {
            item {
                Text(
                    text = "Jornal",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Aqui você encontra informações sobre o Lúpus.",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.calendarDay)

                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Lúpus",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                SampleCarousel(listaDeJornalItems, navController)
            }
            item {
                Text(
                    text = "Notícias",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                SampleCarousel(listaDeNoticiaslItems)
            }

            item {
                Text(
                    text = "Artigos científicos",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                SampleCarousel(listaDeArtigos)
            }
        }
    }
}


val baseImageLink = "https://picsum.photos/300/200"
val listaDeJornalItems = listOf(
    JornalItem(
        imageLink = baseImageLink,
        action = "lupus",
        link = null
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = "treatment",
        link = null
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = "prognostic",
        link = null
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = "lupusType",
        link = null
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = "diagnostic",
        link = null
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = "journey",
        link = null
    )
)

val listaDeNoticiaslItems = listOf(
    JornalItem(
        imageLink = baseImageLink,
        action = null,
        link = "https://www.uol.com.br/vivabem/colunas/lucia-helena/2023/06/01/lupus-depois-de-dez-anos-sem-grande-novidade-surge-um-novo-tratamento.htm"
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = null,
        link = "https://www.camara.leg.br/noticias/978610-projeto-equipara-portadores-de-lupus-a-pessoas-com-deficiencia"
    )
)

val listaDeArtigos = listOf(
    JornalItem(
        imageLink = baseImageLink,
        action = null,
        link = "https://www.scielo.br/j/rbr/a/ZLRCGPGN8cTP8SnYg5qFQ8z/?lang=pt#"
    ),
    JornalItem(
        imageLink = baseImageLink,
        action = null,
        link = "https://www.scielo.br/j/ape/a/WnZzWmYwnMkFH4Kr7j7PVqN/"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleCarousel(carrouselList: List<JornalItem>, navController: NavController? = null) {
    val carouselState = rememberCarouselState(itemCount = { carrouselList.count() })

    HorizontalUncontainedCarousel(
        modifier = Modifier.fillMaxWidth(),
        state = carouselState,
        itemWidth = 200.dp,
        itemSpacing = 20.dp,

        ) { i ->
        val context = LocalContext.current
        val item = carrouselList[i]
        AsyncImage(
            model = item.imageLink,
            contentDescription = "Imagem carregada",
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    item.action?.let { navController?.navigate(it) } ?: run {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                        context.startActivity(intent)
                    }
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun JournalPreview() {
    //Journal()
}