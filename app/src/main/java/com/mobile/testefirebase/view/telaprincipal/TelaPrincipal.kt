package com.mobile.testefirebase.view.telaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.testefirebase.R
import com.mobile.testefirebase.databinding.ActivityTelaPrincipalBinding
import com.mobile.testefirebase.view.formlogin.FormLogin

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarTelaLogin = Intent(this, FormLogin::class.java)
            startActivity(voltarTelaLogin)
            finish()
        }

        binding.btGravarDadosDB.setOnClickListener {

            val usuariosMap = hashMapOf(
                "nome" to "Rodrigo",
                "sobrenome" to "Sarmento",
                "idade" to 28
            )
            db.collection("Usuários").document("Rodrigo").set(usuariosMap)
                .addOnCompleteListener {
                    Log.d("db", "Sucesso ao salvar os dados do usuário!")
                }.addOnFailureListener{

                }
        }

        binding.btLerDadosDB.setOnClickListener {
            db.collection("Usuários").document("Marcos").addSnapshotListener{ documento, error ->
                if (documento != null){
                    binding.txtResultado.text = documento.getString("nome")
                }

            }
        }

        binding.btAtualizarDadosDB.setOnClickListener{
            db.collection("Usuários").document("Marcos")
                .update("nome", "campos").addOnCompleteListener{
                    Log.d("db_update", "Sucesso ao atualizar os dados do usuário!")
                }
        }
    }
}