package com.ncrdesarrollo.ejerciciosvarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {

    private val key = "MY_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        //obtenemos el PreferenceManager
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        btnGuardarPreferencias.setOnClickListener{
            val editor = prefs.edit()
            editor.putString(key, "Hola")
            editor.apply()
        }

        btnMostrarPreferencias.setOnClickListener {
            val myPref = prefs.getString(key, "No hay valor")
            if (myPref != null) {
                showAlert(myPref)
            }
        }

        btnEliminarPreferencias.setOnClickListener {
            val editor = prefs.edit()
            editor.remove(key)
            editor.apply()
        }
    }

    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Valor de Preferencias")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}