package com.example.music_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper (
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
){
    companion object{
        private const val DATABASE_NAME = "musica.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"
    }
    //METODO PARA CREAR LA BASE DE DATOS
    override fun onCreate(p0: SQLiteDatabase?) {
        val crearTablaUsuarios = """
            CREATE TABLE $TABLE_USUARIOS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOMBRE TEXT NOT NULL,
                $COL_CORREO TEXT NOT NULL UNIQUE,
                $COL_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()
        //CREAR TABLA SQL
        p0?.execSQL(crearTablaUsuarios)
    }
    // METODO PARA ACTUALIZAR LA BASE DE DATOS
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(p0)
    }
    fun registrarUsuario(nombre : String, correo : String,   password : String) : Boolean{
        //CREAR VARIABLE PARA QUE LA BASE DE DATOS SE PUEDA MODIFICAR
        val p0 = writableDatabase
        //CREAR VARIABLE PARA RECIBIR LOS DATOS QUE ESCRIBE EL USUARIO
        val datos = ContentValues().apply {
            put(COL_NOMBRE,nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)
        }
        //INSERTAR DATOS EN LA TABLA
        val resultado = p0.insert(TABLE_USUARIOS,null,datos)

        //COMPARAR EL VALOR DEL RESULTADO DE LA INSERCION Y REGRESARLO COMO BOOLEAN
        return resultado != -1L
    }
}