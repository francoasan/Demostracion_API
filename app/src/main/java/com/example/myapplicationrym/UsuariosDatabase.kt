package com.example.myapplicationrym

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Persona::class], version = 1)
abstract class UsuariosDatabase: RoomDatabase() {

    abstract fun PersonaDao():PersonaDao

    companion object{
        private var INSTANCIA: UsuariosDatabase?= null

        fun getDatabase(contexto: Context) : UsuariosDatabase{
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        contexto, UsuariosDatabase::class.java, "base_usuarios_registrados")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }


    }

}