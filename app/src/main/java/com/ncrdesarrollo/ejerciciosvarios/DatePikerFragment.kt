package com.ncrdesarrollo.ejerciciosvarios

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePikerFragment(val listener: (day:Int, month:Int, year:Int) -> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        //para cambiar el color del datePiker añadimos el parametro R.style.DatePickerTheme
        val piker = DatePickerDialog(activity as Context, R.style.DatePickerTheme, this, year, month, day)
        //aqui se pude limitar las fechas maximas y minimas
        piker.datePicker.maxDate = c.timeInMillis //con esto no se puede seleccionar mas de la fecha actual
        piker.datePicker.minDate = c.timeInMillis //con esto no se puede seleccionar menos de la fecha actual
        return piker
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }

}