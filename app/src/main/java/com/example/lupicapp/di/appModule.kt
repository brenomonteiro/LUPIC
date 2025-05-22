package com.example.lupicapp.di

import com.example.lupicapp.GoogleAuthClient
import com.example.lupicapp.UiStateViewModel
import com.example.lupicapp.UserViewModel
import com.example.lupicapp.data.repository.MedicamentoRepository
import com.example.lupicapp.data.repository.UserRepository
import com.example.lupicapp.ui.login.LoginViewModel
import com.example.lupicapp.ui.medicineStock.AddPillViewModel
import com.example.lupicapp.ui.medicineStock.StockEditViewModel
import com.example.lupicapp.ui.medicineStock.StockViewModel
import com.example.lupicapp.ui.register.UserRegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() } // Fornece uma instância do FirebaseAuth
    single { FirebaseDatabase.getInstance() } // Fornece uma instância do FirebaseAuth
    single { GoogleAuthClient(androidContext(), get()) }
    single { UserRepository(get(), get()) }
    single { MedicamentoRepository(get(), get()) }

    viewModel { StockViewModel(get(), get()) }
    viewModel { UserViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { UserRegisterViewModel(get()) }
    viewModel { AddPillViewModel(get()) }
    viewModel { StockEditViewModel(get()) }
    single  { UiStateViewModel() }



}
