package com.example.lupicapp.di

import com.example.lupicapp.GoogleAuthClient
import com.example.lupicapp.ui.login.LoginViewModel
import com.example.lupicapp.ui.register.UserRegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() } // Fornece uma instância do FirebaseAuth
    single { GoogleAuthClient(androidContext(), get()) }
    viewModel { LoginViewModel(get(), get()) } // Injeta o FirebaseAuth no ViewModel
    viewModel { UserRegisterViewModel(get()) } // Injeta o FirebaseAuth no ViewModel
}