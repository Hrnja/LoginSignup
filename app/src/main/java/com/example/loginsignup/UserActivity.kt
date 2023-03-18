package com.example.loginsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsignup.viewmodel.LogSigViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var recView:RecyclerView
    private lateinit var logSigViewModel:LogSigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        recView = findViewById(R.id.recyclerViewID)
        recView.layoutManager = LinearLayoutManager(this)

        logSigViewModel = ViewModelProvider(this).get(LogSigViewModel::class.java)

        val rvAdapter = RVAdapter(logSigViewModel,this)
        recView.adapter = rvAdapter

        logSigViewModel.allUsers.observe(this, Observer { list->
            list?.let {
                rvAdapter.updateList(it)
            }
        })
    }
}