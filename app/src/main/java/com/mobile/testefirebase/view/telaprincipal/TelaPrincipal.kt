package com.mobile.testefirebase.view.telaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.mobile.testefirebase.R
import com.mobile.testefirebase.databinding.ActivityTelaPrincipalBinding
import com.mobile.testefirebase.view.formlogin.FormLogin

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
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
    }
}