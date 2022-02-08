package com.ncrdesarrollo.ejerciciosvarios

import android.app.Application
import androidx.room.Room

class PersonApp: Application() {

    val room = Room
        .databaseBuilder(this, PeopleDB::class.java, "person")
        .build()

}