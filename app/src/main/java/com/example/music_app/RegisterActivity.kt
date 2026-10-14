package com.example.music_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre : EditText
    private lateinit var etCorreo : EditText
    private lateinit var etPassword : EditText

    private lateinit var btnRegistrar : Button

    //CREAR OBJETO DE LA CLASE DE BASE DE DATOS
    private lateinit var dbHelper: DatabaseHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        //INICIAR EL OBJETO DE LA BASE DE DATOS
        dbHelper = DatabaseHelper(this)

        //EVENTO PARA REGISTRAR AL USUARIO
        btnRegistrar.setOnClickListener {
            //LLAMAR AL METODO DE REGISTRAR USUARIO
            registrarUsuario()

        }
    }
    //METODO PARA REGISTRAR A UN USUARIO
    private  fun registrarUsuario(){
        //OBTENER LA INFORMACION QUE ESCRIBE EL USUARIO
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()

        //VALIDAR QUE LOS DATOS TENGAN INFORMACION
        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //LLAMAR A NUESTRA BASE DE DATOS Y GUARDAR EL RESULTADO DE LA INSERCION
        val resultado = dbHelper.registrarUsuario(nombre,correo,password)

        if (resultado){
            //SI EL RESULTADO ES VERDADERO
            Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show()
            //Regresar el usuario a la pantalla anterior
            finish()
        }else{
            Toast.makeText(this,"Registro Fallido", Toast.LENGTH_SHORT).show()
        }

    }
}