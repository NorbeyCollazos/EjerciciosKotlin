package com.ncrdesarrollo.ejerciciosvarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncrdesarrollo.ejerciciosvarios.EjerciciosVarios.Companion.prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000) //esto sirve para retardar
        setTheme(R.style.Theme_EjerciciosVarios)//se debe colocar para que tome el theme principal
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        comprobarSiAGuardadoUsuario()

    }

    private fun comprobarSiAGuardadoUsuario() {
        if (prefs.getName().isNotEmpty()){
            goToResultActivity()
            finish()
        }
    }

    private fun initUI() {
        btnContinuar.setOnClickListener {
            if (!etName.text.isNullOrEmpty()){
                //guardamos el usuario
                prefs.saveName(etName.text.toString())
                prefs.saveVIP(cbVIP.isChecked)

                goToResultActivity()

            }else{
                //pendiente
            }
        }
    }

    private fun goToResultActivity() {
        startActivity(Intent(this,ResultActivity::class.java))
    }
}