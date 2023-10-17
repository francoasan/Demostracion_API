package com.example.myapplicationrym

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationrym.UserApplication.Companion.preferencias
import kotlinx.coroutines.launch

class ActivityInicioSesion: AppCompatActivity() {
    lateinit var etNombreUsuario: EditText
    lateinit var etPasswordUsuario: EditText
    lateinit var btnIniciarSesion: Button
    lateinit var cbRecordar: CheckBox

    //CREACION DEL CANAL
    val channelID = "chat"
    val channelName = "chat"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etPasswordUsuario = findViewById(R.id.etPasswordUsuario)
        cbRecordar = findViewById(R.id.checkboxRecordar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        //CONSTRUCCION DEL CANAL
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelID, channelName, importancia)

            //MANAGER DE NOTIFICACIONES
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }


        btnIniciarSesion.setOnClickListener{
            val enteredUsername = etNombreUsuario.text.toString()
            val enteredPassword = etPasswordUsuario.text.toString()

            if(enteredUsername.isEmpty() || enteredPassword.isEmpty()){
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
            } else {
                lifecycleScope.launch {
                    try {
                        val resultado = checkCredentials(enteredUsername, enteredPassword)
                        if(cbRecordar.isChecked && resultado == true){
                            preferencias.saveUsername(enteredUsername)
                            preferencias.savePassword(enteredPassword)
                            val intentUsuarioCorrecto1 = Intent(this@ActivityInicioSesion, MainActivity::class.java)
                            startActivity(intentUsuarioCorrecto1)
                        }
                        if(cbRecordar.isChecked == false){
                            if(resultado){
                                val intentUsuarioCorrecto = Intent(this@ActivityInicioSesion, MainActivity::class.java)
                                startActivity(intentUsuarioCorrecto)
                            }else{
                                Toast.makeText(this@ActivityInicioSesion, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (e: Exception){
                        Toast.makeText(this@ActivityInicioSesion, "Ocurrio un error durante el inicio de sesion", Toast.LENGTH_LONG).show()
                    }

                }


                /* if(cbRecordar.isChecked && checkCredentials(enteredUsername, enteredPassword)){
                     preferencias.saveUsername(enteredUsername)
                     preferencias.savePassword(enteredPassword)
                     val intentUsuarioCorrecto1 = Intent(this, listaDeElementos::class.java)
                     startActivity(intentUsuarioCorrecto1)
                 }*/

                /*if(checkCredentials(enteredUsername, enteredPassword)){
                    val intentUsuarioCorrecto = Intent(this, listaDeElementos::class.java)
                    startActivity(intentUsuarioCorrecto)
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                }*/

                /* if(cbRecordar.isChecked == false){
                     lifecycleScope.launch {
                         try {
                             val resultado = checkCredentials(enteredUsername, enteredPassword)
                             if(resultado){
                                 val intentUsuarioCorrecto = Intent(this@MainActivity, listaDeElementos::class.java)
                                 startActivity(intentUsuarioCorrecto)
                             }else{
                                 Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                             }
                         } catch(e: Exception){
                             Toast.makeText(this@MainActivity, "Ocurrio un error durante el inicio de sesion", Toast.LENGTH_LONG).show()
                         }
                     }
                 }*/
            }
        }

        //iniciarListaElementos()
        checkUserValues()

        val botonNecesitasUnaCuenta = findViewById<Button>(R.id.botonNecesitasUnaCuenta)
        botonNecesitasUnaCuenta.setOnClickListener{
            val intent2= Intent(this, pantallaRegistro::class.java)
            startActivity(intent2)
        }
    }

    /*private fun startListaElementos(usuarioGuardado: String) {
        val intent = Intent(this, listaDeElementos::class.java)
        intent.putExtra("Nombre", usuarioGuardado)
        startActivity(intent)
    }*/


    fun checkCredentials(username: String, password: String):Boolean{
        val db = UsuariosDatabase.getDatabase(this)
        val userDao = db.PersonaDao()
        val user = userDao.getUserByUsername(username)

        return (user != null && user.password == password)

    }

    fun checkUserValues(){
        if(preferencias.getUsername().isNotEmpty() && preferencias.getPassword().isNotEmpty()){
            val notificacion = NotificationCompat.Builder(this, channelID).also { noti->
                noti.setContentTitle("Recordar Usuario")
                noti.setContentText("Usuario recordado correctamente")
                noti.setSmallIcon(R.drawable.baseline_verified_user_24)
            }.build()

            val notificacionManager = NotificationManagerCompat.from(applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificacionManager.notify(1, notificacion)

            goToDetail()
        }
    }


    /*  fun iniciarListaElementos(){
          val botonIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
          botonIniciarSesion.setOnClickListener{
              accessToDetail()
          }
      }
  */
    /* fun accessToDetail(){
         var textoEtNombreUsuario = etNombreUsuario.text.toString()
         var textoEtPasswordUsuario = etPasswordUsuario.text.toString()
         cbRecordar = findViewById(R.id.checkboxRecordar)
         if(textoEtNombreUsuario.isNotEmpty() || textoEtPasswordUsuario.isNotEmpty()){
             if(cbRecordar.isChecked){
                 //Acceder recordando usuario
                 preferencias.saveUsername(textoEtNombreUsuario)
                 preferencias.savePassword(textoEtPasswordUsuario)
                 goToDetail()
             } else{
                 //Acceder sin recordar
                 goToDetail()
             }
         } else {
             Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
         }
     }
 */
    fun goToDetail(){
        startActivity(Intent(this, MainActivity::class.java))
    }


}
