package com.example.myapplicationrym

import android.app.Application

class UserApplication:Application() {

    companion object {
        lateinit var preferencias: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }

}