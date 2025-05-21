package com.example.lupicapp.ui.medicineStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lupicapp.composeComponents.snackbar.SnackbarController
import com.example.lupicapp.composeComponents.snackbar.SnackbarEvent
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.MedicamentoRepository
import com.example.lupicapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel(
    private val userRepository: UserRepository,
    private val medicamentoRepository: MedicamentoRepository
) : ViewModel() {

    private var _medicamento = MutableStateFlow<List<DrugItem>>(emptyList())
    val medicamentos = _medicamento.asStateFlow()

    //private var _showSnackBar = MutableStateFlow<Boolean>(false)
    //val showSnackBar = _showSnackBar.asStateFlow()

    fun showSnackbar() {

        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = "Operação realizada com sucesso!"
                )
            )
        }
    }

    fun removerMedicamento(id: String) {
        medicamentoRepository.removerMedicamento(id) { sucesso ->
            if (sucesso) {
                showSnackbar()
                carregarMedicamentos()
            }
        }
    }

    fun carregarMedicamentos() {
        userRepository.recuperarMedicamentos { lista ->
            _medicamento.value = lista
        }
    }
}
