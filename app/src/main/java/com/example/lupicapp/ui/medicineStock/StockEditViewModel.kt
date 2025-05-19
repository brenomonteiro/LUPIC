package com.example.lupicapp.ui.medicineStock

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.repository.MedicamentoRepository

class StockEditViewModel(private val repository: MedicamentoRepository) : ViewModel() {

    var medicamento by mutableStateOf<DrugItem?>(null)
        private set

    fun carregarMedicamento(id: String) {
// medicamentos = callback
        repository.buscarMedicamentosPorId(id) {
            Log.i("objeto carregado  funcao", it.toString())
            medicamento = it
        }
    }

//    fun editarMedicamento(id:String,drugItem: DrugItem){
//        repository.atualizarMedicamento(id, drugItem)
//    }

    fun editarMedicamento(id: String, callback: (Boolean) -> Unit) {
        repository.atualizarMedicamento(id, medicamento!!, callback)
    }
}
