package com.example.myapplicationrym

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonaDao {

    @Query("select * from 'Usuarios registrados'")
    fun getAll(): List<Persona>

    @Query("SELECT * FROM `Usuarios registrados` WHERE usuario = :username")
    fun getUserByUsername(username: String): Persona?

    @Query("SELECT * FROM `Usuarios registrados` WHERE usuario = :username")
    fun existeNombreUsuario(username: String): Persona?

    @Insert
    fun insertNuevoUsuario(persona: Persona)

    @Update
    fun update(persona: Persona)

    @Delete
    fun delete(persona: Persona)


}