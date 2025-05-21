package com.example.lupicapp

import AddPill
import Stok
import StokEdit
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lupicapp.composeComponents.snackbar.ObserveAsEvent
import com.example.lupicapp.composeComponents.snackbar.SnackbarController
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.scope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel() // Injeção do ViewModel via Koin
    private val uiStateViewModel: UiStateViewModel by viewModel()
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
                    uiStateViewModel = uiStateViewModel,
//                    snackbarHost = {
//                        SnackbarHost(
//                            hostState = snackbarHostState
//                        )
//
//                    }
                ) {innerPadding ->
                    AppNavigation(Modifier.padding(innerPadding),uiStateViewModel)
                }
            }


        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier,vm:UiStateViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome", modifier = modifier) {
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
            Stok(navController = navController, uiStateViewModel = vm)
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
