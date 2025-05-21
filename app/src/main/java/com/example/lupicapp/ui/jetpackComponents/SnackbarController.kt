//package com.example.lupicapp.ui.jetpackComponents
//
//// SnackbarController.kt
//import androidx.compose.material3.Snackbar
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.graphics.Color
//import com.example.lupicapp.data.model.SnackbarType
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//@Composable
//fun SimpleSnackbar(
//    message: String?,
//    type: SnackbarType = SnackbarType.SUCCESS,
//    onDismiss: () -> Unit = {}
//) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()
//
//    LaunchedEffect(message) {
//        if (!message.isNullOrBlank()) {
//            coroutineScope.launch {
//                snackbarHostState.showSnackbar(message)
//                onDismiss()
//            }
//        }
//    }
//
//    SnackbarHost(
//        hostState = snackbarHostState,
//        snackbar = { data ->
//            Snackbar(
//                snackbarData = data,
//                containerColor = when (type) {
//                    SnackbarType.SUCCESS -> Color(0xFF4CAF50)
//                    SnackbarType.ERROR -> Color.Red
//                },
//                contentColor = Color.White
//            )
//        }
//    )
//}