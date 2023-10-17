package com.example.myapplicationrym

import android.content.Context

class Preferencias(val context:Context) {

    val SHARED_NAME = "My database"
    val SHARED_USERNAME = "username"
    val SHARED_PASSWORD = "password"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveUsername(username:String){
        storage.edit().putString(SHARED_USERNAME, username).apply()
    }

    fun savePassword(password:String){
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }

    fun getUsername():String{
        return storage.getString(SHARED_USERNAME, "")!!
    }

    fun getPassword():String{
        return storage.getString(SHARED_PASSWORD, "")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }


}