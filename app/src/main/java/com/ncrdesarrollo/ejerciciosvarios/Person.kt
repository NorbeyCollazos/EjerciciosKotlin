package com.ncrdesarrollo.ejerciciosvarios

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personas")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val address: String
)