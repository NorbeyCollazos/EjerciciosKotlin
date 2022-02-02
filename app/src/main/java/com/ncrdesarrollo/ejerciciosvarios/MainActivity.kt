package com.ncrdesarrollo.ejerciciosvarios

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.ncrdesarrollo.ejerciciosvarios.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000) //esto sirve para retardar
        setTheme(R.style.Theme_EjerciciosVarios)//se debe colocar para que tome el theme principal
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        etSelectorFecha.setOnClickListener {
            showDatePikerDialog()
        }

        etSelectorHora.setOnClickListener {
            showTimePickerDialog()
        }
    }

    //funciones para el DatePiker
    private fun showDatePikerDialog() {
        val datepiker = DatePikerFragment { day, month, year -> onDateSelected(day, month, year) }
        datepiker.show(supportFragmentManager, "datePiker")
    }

    private fun onDateSelected(day:Int, month:Int, year:Int){
        etSelectorFecha.setText("$day/$month/$year")
    }



    //funciones para el TimePiker
    private fun showTimePickerDialog() {
        val timePicker = TimePikerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun onTimeSelected(time: String) {
        etSelectorHora.setText("$time")
    }


}