package com.ncrdesarrollo.ejerciciosvarios

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPref(context: Context) {

    //obtenemos el PreferenceManager
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = prefs.edit()

    fun guardarString(key:String, texto:String){
        editor.putString(key, texto)
        editor.apply()
    }

    fun obtenerString(key:String): String {
        val myPref = prefs.getString(key, "No hay valor")
        if (myPref != null) {
            return myPref
        }

        return ""
    }

    fun eliminarString(key:String){
        editor.remove(key)
        editor.apply()
    }

}