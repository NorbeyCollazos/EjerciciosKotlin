package com.ncrdesarrollo.ejerciciosvarios

import androidx.room.*

@Dao
interface PersonDao {

    @Query("SELECT * FROM personas")
    fun getAll(): List<Person>

    @Query("SELECT * FROM personas WHERE id = :id")
    fun getById(id: Int): Person

    @Insert
    fun insertListado(people: List<Person>)

    @Update
    fun update(person: Person)

    @Delete
    fun delete(person: Person)
}