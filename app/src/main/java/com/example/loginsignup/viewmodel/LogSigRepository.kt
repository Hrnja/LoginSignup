package com.example.loginsignup.viewmodel

import androidx.lifecycle.LiveData
import com.example.loginsignup.model.LogSigDao
import com.example.loginsignup.model.LogSigEntity

class LogSigRepository(private val dao:LogSigDao) {

    val allUsers: LiveData<List<LogSigEntity>> = dao.getAllUsers()

    suspend fun insert(user:LogSigEntity) {
        dao.insert(user)
    }

    suspend fun delete(user: LogSigEntity) {
        dao.delete(user)
    }

    fun getNamePassword(name:String, password:String) : LiveData<LogSigEntity?> {
        return dao.getNamePassword(name,password)
    }

    fun getPassword(password: String) : LiveData<LogSigEntity?> {
        return dao.getPassword(password)
    }

}