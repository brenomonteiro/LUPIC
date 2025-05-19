package com.example.lupicapp.data.repository

import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository(
    private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase
) {

    fun salvarUsuarioLogado() {
        val user = firebaseAuth.currentUser ?: return
        val usuario = User(
            id = user.uid, nome = user.displayName ?: "", email = user.email ?: ""
        )

        val referencia = firebaseDatabase.reference
        referencia.child("usuarios").child(usuario.id).setValue(usuario)


    }

    fun getUsuarioAtual(): User? {
        val user = firebaseAuth.currentUser ?: return null
        return User(
            id = user.uid, nome = user.displayName ?: "", email = user.email ?: ""
        )
    }

    fun recuperarMedicamentos(callback: (List<DrugItem>) -> Unit) {

        val uid = firebaseAuth.currentUser?.uid ?: return
        val reference =
            firebaseDatabase.reference
                .child("usuarios")
                .child(uid)
                .child("medicamentos")
//// retornar o caso de erro tbm
        reference.get().addOnSuccessListener { snapshot ->
            val lista = mutableListOf<DrugItem>()
            snapshot.children.forEach {
                val medicamento = it.getValue(DrugItem::class.java)
                medicamento?.let {
                    lista.add(it)
                }

            }
            callback(lista)
        }.addOnFailureListener {

        }
    }


    fun salvarMedicamento(medicamento: DrugItem, callback: (Boolean) -> Unit) {
        val uid = firebaseAuth.currentUser?.uid ?: return
        val reference =
            firebaseDatabase.reference
                .child("usuarios")
                .child(uid)
                .child("medicamentos")
                .child(medicamento.id)

        //////// colocar caso de erro tbm
        reference.setValue(medicamento)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

}