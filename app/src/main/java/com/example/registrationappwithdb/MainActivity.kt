package com.example.registrationappwithdb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.registration_button)

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPassword.text.toString().trim()

            if (login == "" || email == "" || pass == "") {
                Toast.makeText(this, "Заполните поля!", Toast.LENGTH_LONG).show()
            }
            else {
                val user = User(login, email, pass)

                val db = DbHelper(this, null)
                db.dbWrite(user)
                Toast.makeText(this, "Пользователь $login добавлен!", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()

            }

        }
    }
}