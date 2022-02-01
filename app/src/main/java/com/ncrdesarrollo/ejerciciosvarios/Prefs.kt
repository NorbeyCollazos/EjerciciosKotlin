package com.ncrdesarrollo.ejerciciosvarios

import android.content.Context

class Prefs(val context: Context) {

    val NAME_PREFERENCES = "mypreferences"

    val SHARED_USER_NAME = "username"
    val SHARED_VIP = "vip"

    val storage = context.getSharedPreferences(NAME_PREFERENCES, 0)

    //funciones
    fun saveName(name: String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveVIP(vip: Boolean){
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getName(): String{
        return storage.getString(SHARED_USER_NAME, "")!!
    }

    fun getVIP():Boolean{
        return storage.getBoolean(SHARED_VIP, false)
    }

    fun borrarTodasPreferencias(){
        storage.edit().clear().apply()
    }
}