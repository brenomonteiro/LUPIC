package com.example.lupicapp.ui.medicineStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.composeComponents.snackbar.SnackbarController
import com.example.lupicapp.composeComponents.snackbar.SnackbarEvent
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class AddPillViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    fun salvarMedicamento(medicamento: DrugItem, onResult: () -> Unit) {
        userRepository.salvarMedicamento(medicamento) {
            if (it) {
                showSnackbar("Operação realizada com sucesso !")
            }else{
                showSnackbar("Falha ao adicionar medicamento")
            }
            onResult()
        }
    }

    fun showSnackbar(message: String) {
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = message
                )
            )
        }
    }
}
