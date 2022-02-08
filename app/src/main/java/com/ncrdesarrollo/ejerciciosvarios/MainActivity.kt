package com.ncrdesarrollo.ejerciciosvarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.ncrdesarrollo.ejerciciosvarios.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    val app = applicationContext as PersonApp


    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000) //esto sirve para retardar
        setTheme(R.style.Theme_EjerciciosVarios)//se debe colocar para que tome el theme principal
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val all = app.room.personDao().getAll()

    }


}