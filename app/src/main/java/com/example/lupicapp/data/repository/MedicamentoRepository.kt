package com.example.lupicapp.data.repository

import android.util.Log
import com.example.lupicapp.data.model.DrugItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MedicamentoRepository(private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase) {
    val uid = firebaseAuth.currentUser?.uid  ?: ""



    private val db = firebaseDatabase.reference.child("usuarios")
        .child(uid)
        .child("medicamentos")

    fun buscarMedicamentosPorId(id: String, callback: (DrugItem?) -> Unit) {
        db.child(id).get().addOnSuccessListener { dataSnapshot ->
            val medicamento = dataSnapshot.getValue(DrugItem::class.java)
            callback(medicamento)

        }.addOnFailureListener{
            callback(null)
        }
    }

    fun atualizarMedicamento(id: String, medicamento: DrugItem, callback: (Boolean) -> Unit){
        db.child(id).setValue(medicamento)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }    }
}