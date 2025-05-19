package com.example.lupicapp.data.repository

import com.example.lupicapp.data.model.DrugItem
import com.example.lupicapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository(
    private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase
) {

    fun salvarUsuarioLogado(onResult: (Boolean, User?) -> Unit) {

        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser == null) {
            onResult(false, null)
            return
        }
        val uid = firebaseUser.uid

        val refUser = firebaseDatabase.reference.child("usuarios").child(uid)
        refUser.get().addOnSuccessListener {
            if (it.exists()) {
                val usuario = it.getValue(User::class.java)
                onResult(true, usuario)
            }else{

                val usuario = User(
                    id = firebaseUser.uid, nome = firebaseUser.displayName ?: "", email = firebaseUser.email ?: ""
                )

                refUser.setValue(usuario).addOnCompleteListener {
                    if (it.isSuccessful){
                        onResult(true, usuario)
                    }else{
                        onResult(false,null)
                    }
                }
            }

        }.addOnFailureListener {
            onResult(false, null)
        }
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

        reference.setValue(medicamento)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

}