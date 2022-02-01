package com.ncrdesarrollo.ejerciciosvarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncrdesarrollo.ejerciciosvarios.adapter.SuperHeroAdapter
import com.ncrdesarrollo.ejerciciosvarios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000) //esto sirve para retardar
        setTheme(R.style.Theme_EjerciciosVarios)//se debe colocar para que tome el theme principal
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList){onItemSelected(it)}
    }

    fun onItemSelected(superHero: SuperHero){
        Toast.makeText(this, superHero.superHero, Toast.LENGTH_LONG).show()
    }
}