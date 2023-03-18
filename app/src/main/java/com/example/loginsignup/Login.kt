package com.example.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginsignup.viewmodel.LogSigViewModel


class Login : AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    private lateinit var register: TextView
    private lateinit var logSigViewModel: LogSigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        name = findViewById(R.id.name_login)
        password = findViewById(R.id.password_login)
        login = findViewById(R.id.button_login)
        register = findViewById(R.id.register_on_login)

        logSigViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(LogSigViewModel::class.java)

        login.setOnClickListener{

            if(name.text.isNotEmpty() && password.text.isNotEmpty()) {
                val pname = name.text.toString()
                val ppassword = password.text.toString()

                logSigViewModel.getNamePassword(pname,ppassword).observe(this,Observer{user->
                    if(user != null) {
                        startActivity(Intent(this,UserActivity::class.java))
                        this.finish()
                    }
                    else {
                        Toast.makeText(this,"Error, user with that name and password don't exist in database", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else if(name.text.isNotEmpty() && password.text.isEmpty()) {
                Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show()
            }
            else if(name.text.isEmpty() && password.text.isNotEmpty()) {
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
            }
            else  {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            this.finish()
        }

    }
}

