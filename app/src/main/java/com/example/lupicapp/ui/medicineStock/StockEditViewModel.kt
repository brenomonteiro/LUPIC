package com.example.lupicapp.ui.medicineStock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.composeComponents.snackbar.SnackbarController
import com.example.lupicapp.composeComponents.snackbar.SnackbarEvent
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.MedicamentoRepository
import kotlinx.coroutines.launch

class StockEditViewModel(private val repository: MedicamentoRepository) : ViewModel() {

    var medicamento by mutableStateOf<DrugItem?>(null)
        private set

    fun carregarMedicamento(id: String) {
        repository.buscarMedicamentosPorId(id) {
            medicamento = it
        }
    }

    fun editarMedicamento(id: String, callback: () -> Unit) {
        repository.atualizarMedicamento(id, medicamento!!){
            if (it) {
                showSnackbar("Operação realizada com sucesso !")
            }else{
                showSnackbar("Falha ao adicionar medicamento")
            }
            callback()
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
