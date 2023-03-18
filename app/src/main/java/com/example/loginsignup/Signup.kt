package com.example.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.loginsignup.model.LogSigDatabase
import com.example.loginsignup.model.LogSigEntity
import com.example.loginsignup.viewmodel.LogSigViewModel
import kotlinx.coroutines.*
import kotlin.properties.Delegates


class Signup : AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var lastname:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var signup:Button
    lateinit var logSigViewModel: LogSigViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        name = findViewById(R.id.name_signup)
        lastname = findViewById(R.id.lastname_signup)
        email  = findViewById(R.id.email_signup)
        password = findViewById(R.id.password_signup)
        signup = findViewById(R.id.button_signup)

        logSigViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(LogSigViewModel::class.java)

        signup.setOnClickListener{

            if(name.text.isNotEmpty() && lastname.text.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty()) {
                val pname = name.text.toString()
                val plastname = lastname.text.toString()
                val pemail = email.text.toString()
                val ppassword = password.text.toString()
                val activity = this@Signup



                logSigViewModel.getPassword(ppassword).observeOnce(this, Observer { user ->
                    if (user != null) { //User != null -> postoji u bazi
                        Toast.makeText(activity, "Can't add user with this password. Change password", Toast.LENGTH_LONG).show()
                    } else {
                        logSigViewModel.addUser(LogSigEntity(pname, plastname, pemail, ppassword))
                        Toast.makeText(applicationContext, "Successfully signup", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, UserActivity::class.java))
                        activity.finish()
                    }
                })
            }

            else if(name.text.isNotEmpty() && lastname.text.isNotEmpty() && email.text.isNotEmpty()) {
                Toast.makeText(this,"Enter password", Toast.LENGTH_SHORT).show()
            }
            else if(name.text.isNotEmpty() && lastname.text.isNotEmpty()) {
                Toast.makeText(this,"Enter email and password", Toast.LENGTH_SHORT).show()
            }
            else if(name.text.isNotEmpty()) {
                Toast.makeText(this,"Enter lastname, email and password", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this,"All fields are empty", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<T>) {
        observe(owner, object : Observer<T> {
            override fun onChanged(t: T) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })


    }
}