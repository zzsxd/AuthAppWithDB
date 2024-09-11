package com.example.registrationappwithdb

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

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPassword: EditText = findViewById(R.id.user_password_auth)
        val button: Button = findViewById(R.id.auth_button)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPassword.text.toString().trim()

            if (login == "" || pass == "") {
                Toast.makeText(this, "Заполните поля!", Toast.LENGTH_LONG).show()
            } else {

                val db = DbHelper(this, null)
                val isAuth = db.dbRead(login, pass)
                if (isAuth) {
                    Toast.makeText(this, "Вы успешно авторизовались!", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this, "Неверные данные!", Toast.LENGTH_LONG).show()
                }

                userLogin.text.clear()
                userPassword.text.clear()

            }
        }

    }
}