package com.example.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var login: Button
    private lateinit var register:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.welcome_login)
        register = findViewById(R.id.welcome_register)

        login.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            this.finish()
        }

        register.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
            this.finish()
        }

    }
}