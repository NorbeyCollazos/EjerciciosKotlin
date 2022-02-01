package com.ncrdesarrollo.ejerciciosvarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ncrdesarrollo.ejerciciosvarios.EjerciciosVarios.Companion.prefs
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initUI()
    }

    private fun initUI() {
        val userName = prefs.getName()
        tvName.text = "Bienvenido $userName"

        if (prefs.getVIP()){
            mostrarColorFondo()
        }

        btnCerrarSesion.setOnClickListener {
            prefs.borrarTodasPreferencias()
            onBackPressed()
        }
    }

    private fun mostrarColorFondo() {
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.amarillo))
    }
}