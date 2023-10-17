package com.example.myapplicationrym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationrym.Persona
import com.example.myapplicationrym.R
import com.example.myapplicationrym.UsuariosDatabase

class pantallaRegistro : AppCompatActivity() {

    lateinit var etNombre:EditText
    lateinit var etApellido:EditText
    lateinit var etEdad:EditText
    lateinit var etUsuarioRegistrado:EditText
    lateinit var etPasswordRegistrada:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        etNombre = findViewById<EditText>(R.id.etNombreRegistro)
        etApellido = findViewById<EditText>(R.id.etApellidoRegistro)
        etEdad = findViewById<EditText>(R.id.etEdadRegistro)
        etUsuarioRegistrado = findViewById<EditText>(R.id.etUsuarioRegistro)
        etPasswordRegistrada = findViewById<EditText>(R.id.etPasswordRegistro)


        val botonRegistrar = findViewById<Button>(R.id.btnRegistro)
        botonRegistrar.setOnClickListener {
            var nombre = etNombre.text.toString()
            var apellido = etApellido.text.toString()
            var edad = etEdad.text.toString()
            var usuarioRegistrado = etUsuarioRegistrado.text.toString()
            var passwordRegistrada = etPasswordRegistrada.text.toString()

            if(nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || usuarioRegistrado.isEmpty() || passwordRegistrada.isEmpty()){
                Toast.makeText(this, "Faltan datos por completar", Toast.LENGTH_LONG).show()
            } else {
                if(checkUsuarioExiste(usuarioRegistrado) == null){
                    var nuevoRegistro = Persona(nombre, apellido, edad, usuarioRegistrado, passwordRegistrada)
                    UsuariosDatabase.getDatabase(this).PersonaDao().insertNuevoUsuario(nuevoRegistro)
                    var intentRegistroCorrecto = Intent(this, ActivityUsuarioCorrecto::class.java)
                    startActivity(intentRegistroCorrecto)
                }else{
                    Toast.makeText(this, "El usuario ya esta en uso", Toast.LENGTH_LONG).show()
                }
            }
        }

        val botonVolverALogin = findViewById<Button>(R.id.btnVolverALogin)
        botonVolverALogin.setOnClickListener{onBackPressed()}





    }

    /* fun nombreUsuarioExiste(nombreUsuario: String){
         val db = UsuariosDatabase.getDatabase(this)
         val userDao = db.PersonaDao()
         val existe = userDao.existeNombreUsuario(nombreUsuario)

             if(existe){
                 Toast.makeText(this, "El nombre de usuario ya esta en uso", Toast.LENGTH_LONG).show()
             } else{
                 var nuevoRegistro = Persona(nombre, apellido, edad, usuarioRegistrado, passwordRegistrada)
                 UsuariosDatabase.getDatabase(this).PersonaDao().insertNuevoUsuario(nuevoRegistro)
                 var intentRegistroCorrecto = Intent(this, ActivityUsuarioCorrecto::class.java)
                 startActivity(intentRegistroCorrecto)
             }
         }*/

    fun checkUsuarioExiste(username: String): Persona?{
        val db = UsuariosDatabase.getDatabase(this)
        val userDao = db.PersonaDao()
        val user = userDao.getUserByUsername(username)

        return user
    }
}
