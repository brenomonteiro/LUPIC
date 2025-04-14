import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lupicapp.AppScaffold
import com.example.lupicapp.R
import com.example.lupicapp.ui.jetpackComponents.CustomTextField

@Composable
fun StokEdit(navController: NavController) {
    AppScaffold(navController = navController,showBackArrow = true){innerPadding, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), // Fundo branco para garantir a separação
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Enalapril",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            colorResource(id = R.color.purple_100),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        // verticalArrangement = Arrangement.Center
                        // modifier = Modifier.
                        //.fillMaxSize()
//                        .background(
//                            colorResource(id = R.color.purple_100),
//                            shape = RoundedCornerShape(12.dp)
//                        )
                        //.padding(start = 34.dp, end = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            //modifier = Modifier.padding(top = 16.dp)
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

                        Row(
                            modifier = Modifier.padding(top = 16.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.comprimido),
                                contentDescription = "Imagem à esquerda",
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "14 comprimidos",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                    CustomTextField(Modifier.fillMaxSize())

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
                        text = "Posologia", fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    CustomTextField(Modifier.fillMaxSize())
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(Modifier.fillMaxSize())
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            top = 8.dp,
                            start = 16.dp
                        )
                    ) {
                        Text(
                            text = "1 dose",
                            Modifier.weight(1f)
                        )
                        CustomTextField(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                        )
                    }


                }
            }
            item {
                Button(
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 16.dp),
                    onClick = { /* Ação ao clicar */ }
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
    //StokEdit()
}