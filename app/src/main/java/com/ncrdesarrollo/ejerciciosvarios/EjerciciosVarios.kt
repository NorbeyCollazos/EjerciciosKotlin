package com.ncrdesarrollo.ejerciciosvarios

import android.app.Application

class EjerciciosVarios: Application() {

    //creamos el companion object para que se pueda acceder desde cualquier activity
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}