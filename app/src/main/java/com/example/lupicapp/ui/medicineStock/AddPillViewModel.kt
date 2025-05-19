package com.example.lupicapp.ui.medicineStock

import androidx.lifecycle.ViewModel
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.UserRepository

class AddPillViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    fun salvarMedicamento(medicamento: DrugItem, onResult: (Boolean) -> Unit) {
        userRepository.salvarMedicamento(medicamento, onResult)
    }
}
