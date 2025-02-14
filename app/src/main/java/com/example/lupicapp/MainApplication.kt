package com.example.lupicapp

import android.app.Application
import com.example.lupicapp.di.appModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicializa o Firebase
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule) // Carrega os módulos do Koin
        }
    }
}