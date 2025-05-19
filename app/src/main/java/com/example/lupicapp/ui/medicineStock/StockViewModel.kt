package com.example.lupicapp.ui.medicineStock


import androidx.lifecycle.ViewModel
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.MedicamentoRepository
import com.example.lupicapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StockViewModel(
    private val userRepository: UserRepository,
    private val medicamentoRepository: MedicamentoRepository
) : ViewModel() {

    private var _medicamento = MutableStateFlow<List<DrugItem>>(emptyList())
    val medicamentos = _medicamento.asStateFlow()


    fun removerMedicamento(id: String, callback: (Boolean) -> Unit) {
        medicamentoRepository.removerMedicamento(id) { sucesso ->
            if (sucesso) {
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