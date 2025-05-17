package com.example.lupicapp.ui.login

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.GoogleAuthClient
import com.example.lupicapp.data.model.User
import com.example.lupicapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val googleAuthClient: GoogleAuthClient,
    private val userRepository: UserRepository
) : ViewModel() {
    var loginState = mutableStateOf<LoginResult>(LoginResult.Idle)
        private set

    fun logion(email: String, password: String) {
        viewModelScope.launch {
            loginState.value = LoginResult.Loading

            //val result = authRepository.signIn(email, password)
           // loginState.value = result
            Log.i("credenciais", "$email $password")
        }


    }

    fun login(email: String, password: String) {

        viewModelScope.launch {
            loginState.value = LoginResult.Loading
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loginState.value = LoginResult.Success
                    } else {
                        loginState.value =
                            LoginResult.Error(task.exception?.message ?: "Erro desconhecido")
                    }
                }
            } catch (e: Exception) {
                loginState.value = LoginResult.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    suspend fun getSignInIntent(): IntentSender? {
        return googleAuthClient.signIn()
    }


    fun signInWithGoogle(intent: Intent?, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            userRepository.salvarUsuarioLogado()
            val user = googleAuthClient.signInWithIntent(intent)
            onResult(user != null)
        }
    }

    fun signOut() {
        googleAuthClient.signOut()
    }

}

// Estados possíveis do login
sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}