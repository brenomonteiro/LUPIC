package com.example.lupicapp.ui.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.ui.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class UserRegisterViewModel(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    var loadState = mutableStateOf<LoginResult>(LoginResult.Idle)
        private set


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _repeatEmail = MutableStateFlow<String?>(null)
    val repeatEmail: StateFlow<String?> = _repeatEmail

    private val _repeatEmailError = MutableStateFlow(false)
    val repeatEmailError: StateFlow<Boolean> = _repeatEmailError

    private val _repeatEmailRequiredField = MutableStateFlow("")
    val repeatEmailRequiredField: StateFlow<String> = _repeatEmailRequiredField

    private val _password = MutableStateFlow<String?>(null)
    val password: StateFlow<String?> = _password

    private val _repeatPassword = MutableStateFlow<String?>(null)
    val repeatPassword: StateFlow<String?> = _repeatPassword

    private val _repeatPasswordRequiredField = MutableStateFlow("")
    val repeatPasswordRequiredField: StateFlow<String> = _repeatPasswordRequiredField

    private val _repeatPasswordError = MutableStateFlow(false)
    val repeatPasswordError: StateFlow<Boolean> = _repeatPasswordError

    private val _emailError = MutableStateFlow(false)

    val emailError: StateFlow<Boolean> = _emailError

    private val _passwordError = MutableStateFlow(false)
    val passwordError: StateFlow<Boolean> = _passwordError

    private val _loginState = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginState: StateFlow<LoginResult> = _loginState

    private val _shouldValidate = MutableStateFlow(false)

    data class ValidationErrors(
        val emailError: String?,
        val repeatEmailError: String?,
        val passwordError: String?,
        val repeatPasswordError: String?
    )


    init {
        viewModelScope.launch {
            combine(_email, _repeatEmail, _password, _repeatPassword) { email, repeatEmail, password, repeatPassword ->
                val emailError = if (email.isNullOrBlank()) null else null
                val repeatEmailError = when {
                    repeatEmail == null -> null // Primeira vez, não mostra erro
                    repeatEmail.isBlank() -> "Campo obrigatório"
                    repeatEmail != email -> "Os e-mails devem ser iguais"
                    else -> null
                }

                val passwordError = if (password.isNullOrBlank()) null else null
                val repeatPasswordError = when {
                    repeatPassword == null -> null // Primeira vez, não mostra erro
                    repeatPassword.isBlank() -> "Campo obrigatório"
                    repeatPassword != password -> "As senhas devem ser iguais"
                    else -> null
                }

                ValidationErrors(emailError, repeatEmailError, passwordError, repeatPasswordError)
            }.collect { errors ->
                _repeatEmailError.value = errors.repeatEmailError != null
                _repeatEmailRequiredField.value = errors.repeatEmailError ?: ""

                _repeatPasswordError.value = errors.repeatPasswordError != null
                _repeatPasswordRequiredField.value = errors.repeatPasswordError ?: ""
            }
        }
    }




//    init {
//        viewModelScope.launch {
//            combine(_email, _repeatEmail) { email, repeatEmail ->
//                // Se o campo de repetição de e-mail ainda for null, não validamos
//                if (repeatEmail == null) {
//                    _repeatEmailError.value = false
//                    ""
//                } else{
//                    // Validação se o campo não for vazio e o e-mail não for igual
//                    when {
//                        repeatEmail == "" -> {
//                            _repeatEmailError.value = true
//                            "Campo obrigatório"
//                        }
//                        email != repeatEmail -> {
//                            _repeatEmailError.value = true
//                            "Campo diferente do email, tem que ser igual"
//                        }
//                        else -> {
//                            _repeatEmailError.value = false
//                            ""
//                        }
//                    }
//                }
//            }.collect { mensagem ->
//                _repeatEmailRequiredField.value = mensagem
//            }
//        }
//
//        viewModelScope.launch {
//            combine(_password, _repeatPassword) { password, repeatPassword ->
//                // Se o campo de repetição de e-mail ainda for null, não validamos
//                if (repeatPassword == null) {
//                    _repeatPasswordError.value = false
//                    ""
//                }else if(password == null){
//                    _passwordError.value = false
//                    ""
//                } else{
//                    // Validação se o campo não for vazio e o e-mail não for igual
//                    when {
//
//                        repeatPassword == "" -> {
//                            _repeatPasswordError.value = true
//                            "Campo obrigatório"
//                        }
//                        password != repeatPassword -> {
//                            _repeatPasswordError.value = true
//                            "Campo diferente da senha, tem que ser igual"
//                        }
//                        else -> {
//                            _repeatPasswordError.value = false
//                            ""
//                        }
//                    }
//                }
//            }.collect { mensagem ->
//                _repeatPasswordRequiredField.value = mensagem
//            }
//        }
//    }

    fun validateFields() {
        _shouldValidate.value = true
    }

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

        if ( !_emailError.value && !_repeatEmailError.value && !_passwordError.value && !_repeatPasswordError.value && !_email.value.isBlank() &&  !_password.value.isNullOrBlank() ){
            registerWithEmailFirebase(_email.value, _password.value!!)
        }

    }

    fun registerWithEmailFirebase(email: String, password: String) {
        viewModelScope.launch {
            loadState.value = LoginResult.Loading

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _loginState.value = if (task.isSuccessful) {
                        LoginResult.Success
                    } else {
                        Log.i(" entrou aquii", task.exception?.message?: "rtrtrt")
                        LoginResult.Error(task.exception?.message ?: "Erro desconhecido")
                    }
                }
        }
    }
}