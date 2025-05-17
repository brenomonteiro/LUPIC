package com.example.lupicapp

import androidx.lifecycle.ViewModel
import com.example.lupicapp.data.repository.UserRepository

class UserViewModel(
    private val repository: UserRepository
): ViewModel() {

    fun salvarUsuario(){
        repository.salvarUsuarioLogado()
    }

    fun getUsuarioLogado() = repository.getUsuarioAtual()
}