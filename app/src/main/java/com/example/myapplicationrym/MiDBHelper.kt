package com.example.myapplicationrym

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//import android.content.ContentValues


class MiDBHelper (context: Context) : SQLiteOpenHelper(context, "MiBaseDeDatos", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        //CREA LA TABLA DE USUARIOS SI NO EXISTE
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre_usuario TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //ACTUALIZACIONES DE LA BD SI ES NECESARIO
    }

    private fun usuarioExiste(usuario: String): Boolean{
        val db = readableDatabase
        val query = "SELECT * FROM usuarios WHERE nombre_usuario = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(usuario))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}