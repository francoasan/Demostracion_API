package com.example.myapplicationrym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationrym.R

class ActivityUsuarioCorrecto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_correcto)

        val btnVolverALogin1 = findViewById<Button>(R.id.btnVolverALogin2)

        btnVolverALogin1.setOnClickListener{
            var intentVolverALogin1 = Intent(this, MainActivity::class.java)
            startActivity(intentVolverALogin1)
        }



    }
}