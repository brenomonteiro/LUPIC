package com.example.lupicapp

import AddPill
import Stok
import StokEdit
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        // Navigation controller
        // FirebaseApp.initializeApp(this)
//
        // var referencia = FirebaseDatabase.getInstance().getReference()
        //   val navController = rememberNavController()
        // enableEdgeToEdge()


        installSplashScreen()
        setContent {
            LupicappTheme(false) {
                val navController: NavHostController = rememberNavController()

//                val snackbarHostState = remember {
//                    SnackbarHostState()
//                }
//                val scope = rememberCoroutineScope()
//                ObserveAsEvent(
//                    flow = SnackbarController.events,
//                    snackbarHostState
//                ) { event ->
//                    scope.launch {
//                        snackbarHostState.currentSnackbarData?.dismiss()
//
//                        val result = snackbarHostState.showSnackbar(
//                            message = event.message,
//                            actionLabel = event.action?.name,
//                            duration = SnackbarDuration.Short
//                        )
//
//                        if(result == SnackbarResult.ActionPerformed){
//                            event.action?.action?.invoke()
//                        }
//                    }
//                }
//                Scaffold(modifier = Modifier.fillMaxSize(),
//                    snackbarHost = {
//                        SnackbarHost(
//                            hostState = snackbarHostState
//                        )
//
//                    }) {innerPadding ->
//                    AppNavigation(Modifier.padding(innerPadding))
//                }
                AppScaffold(
                    navController
                    //uiStateViewModel = uiStateViewModel,
//                    snackbarHost = {
//                        SnackbarHost(
//                            hostState = snackbarHostState
//                        )
//
//                    }
                ) {innerPadding ->
                    AppNavigation(Modifier.padding(innerPadding),navController)
                }
            }


        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier, navController: NavHostController) {
   // val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome", modifier = modifier) {
        composable("welcome") {
            Welcome(
                navController = navController,
                //uiStateViewModel = vm
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
            HomeScreen(navController = navController)
        }

        composable("jornal") {
            Journal(navController = navController)
        }
        composable("lupus") {
            Lupus(navController = navController)
        }
        composable("treatment") {
            Treatment(navController = navController)
        }
        composable("prognostic") {
            Prognostic(navController = navController)
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
            Stok(navController = navController,
                )
        }

        composable("AddPill") {
            AddPill(navController = navController)
        }
        composable(
            route = "editar_medicamento/{medicamentoId}",
            arguments = listOf(
                navArgument("medicamentoId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("medicamentoId") ?: ""

            StokEdit(navController = navController, id = id)
        }
    }
}
