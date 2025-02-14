package com.example.lupicapp.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.ui.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserRegisterViewModel(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    var loadState = mutableStateOf<LoginResult>(LoginResult.Idle)
        private set


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _repeatEmail = MutableStateFlow("")
    val repeatEmail: StateFlow<String> = _repeatEmail

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError

    private val _passwordError = MutableStateFlow(false)
    val passwordError: StateFlow<Boolean> = _passwordError

    private val _loginState = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginState: StateFlow<LoginResult> = _loginState

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _emailError.value = newEmail.isBlank()
    }

    fun onRepeatEmailChange(newRepeatEmail: String) {
        _repeatEmail.value = newRepeatEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _passwordError.value = newPassword.isBlank()
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _repeatPassword.value = newRepeatPassword
    }

    fun registerUser() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _emailError.value = _email.value.isBlank()
            _passwordError.value = _password.value.isBlank()
            return
        }

        if (_email.value != _repeatEmail.value) {
            _emailError.value = true
            return
        }

        if (_password.value != _repeatPassword.value) {
            _passwordError.value = true
            return
        }

        _loginState.value = LoginResult.Loading

//        viewModelScope.launch {
//            try {
//                // Simula um cadastro
//                delay(2000)
//                _loginState.value = LoginResult.Success
//            } catch (e: Exception) {
//                _loginState.value = LoginResult.Error(e.message ?: "Erro desconhecido")
//            }
//        }
//    }

//    fun registerUser(email: String, password: String) {
//        viewModelScope.launch {
//            loginState.value = LoginResult.Loading
//
//            firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    loginState.value = if (task.isSuccessful) {
//                        LoginResult.Success
//                    } else {
//                        LoginResult.Error(task.exception?.message ?: "Erro desconhecido")
//                    }
//                }
//        }
//    }
    }
}