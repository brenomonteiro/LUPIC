package com.example.lupicapp

import AddPill
import Stok
import StokEdit
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lupicapp.ui.home.HomeScreen
import com.example.lupicapp.ui.journal.Diagnostic
import com.example.lupicapp.ui.journal.Journal
import com.example.lupicapp.ui.journal.Lupus
import com.example.lupicapp.ui.journal.LupusType
import com.example.lupicapp.ui.journal.PatientJourney
import com.example.lupicapp.ui.journal.Prognostic
import com.example.lupicapp.ui.journal.Treatment
import com.example.lupicapp.ui.login.Login
import com.example.lupicapp.ui.login.LoginViewModel
import com.example.lupicapp.ui.register.UserRegister
import com.example.lupicapp.ui.theme.LupicappTheme
import com.example.lupicapp.ui.welcome.Welcome
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel() // Injeção do ViewModel via Koin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Navigation controller
       //FirebaseApp.initializeApp(this)
//
      // var referencia = FirebaseDatabase.getInstance().getReference()
            //   val navController = rememberNavController()
        //enableEdgeToEdge()
       installSplashScreen()
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
            HomeScreen(navController = navController,)
        }

        composable("jornal") {
            Journal(navController = navController)
        }
        composable("lupus") {
            Lupus(navController = navController,)
        }
        composable("treatment") {
            Treatment(navController = navController,)
        }
        composable("prognostic") {
            Prognostic(navController = navController,)
        }
        composable("lupusType") {
            LupusType(navController = navController)
        }
        composable("diagnostic") {
            Diagnostic(navController = navController)
        }
        composable("journey") {
            PatientJourney(navController = navController)
        }

        composable("Stock") {
            Stok(navController = navController)
        }

        composable("AddPill") {
            AddPill(navController = navController)
        }
        composable(route = "editar_medicamento/{medicamentoId}",
            arguments = listOf(navArgument("medicamentoId"){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("medicamentoId") ?: ""

            StokEdit(navController = navController, id = id)
        }
    }
}
