package com.example.myapplicationrym

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Usuarios registrados")

data class Persona(
    @ColumnInfo(name = "Nombre") var nombre:String,
    @ColumnInfo(name = "Apellido") var apellido:String,
    @ColumnInfo(name = "Edad") var edad:String,
    @ColumnInfo(name = "Usuario") var username:String,
    @ColumnInfo(name = "Password") var password:String){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
