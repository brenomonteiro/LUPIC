package com.example.lupicapp.ui.medicineStock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.UserRepository

class StockViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    var medicamentos by mutableStateOf<List<DrugItem>>(emptyList())
        private set
//    init {
//        userRepository.salvarMedicamento(
//            DrugItem(name="cloroquina",
//            pillsPerDay = "2",
//            totalPills = "5"))
//    }



    fun carregarMedicamentos() {
        userRepository.recuperarMedicamentos { lista ->
            medicamentos = lista
        }
    }

}