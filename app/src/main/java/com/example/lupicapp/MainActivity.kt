package com.example.lupicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lupicapp.ui.home.HomeScreen
import com.example.lupicapp.ui.login.Login
import com.example.lupicapp.ui.login.LoginViewModel
import com.example.lupicapp.ui.register.UserRegister
import com.example.lupicapp.ui.theme.LupicappTheme
import com.example.lupicapp.ui.welcome.Welcome
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModel() // Injeção do ViewModel via Koin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Navigation controller
       //FirebaseApp.initializeApp(this)
//
      // var referencia = FirebaseDatabase.getInstance().getReference()
            //   val navController = rememberNavController()
        enableEdgeToEdge()
       // installSplashScreen()
        setContent {
            LupicappTheme(false) {
                AppNavigation()            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {

        composable("welcome") {
            Welcome(
                navController = navController,
//                onLoginSuccess = { navController.navigate("home")
//
//                }
            )
        }
        composable("login") {
            Login(
                navController = navController,
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("register") {
            UserRegister(
                navController = navController,
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LupicappTheme {
        Greeting("Android")
    }
}