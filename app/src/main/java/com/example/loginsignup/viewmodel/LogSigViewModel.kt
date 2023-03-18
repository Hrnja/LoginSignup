package com.example.loginsignup.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginsignup.model.LogSigDatabase
import com.example.loginsignup.model.LogSigEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogSigViewModel(application: Application) : AndroidViewModel(application) {
    val allUsers: LiveData<List<LogSigEntity>>
    val repository: LogSigRepository

    init {
        val dao = LogSigDatabase.getDatabase(application).getDao()
        repository = LogSigRepository(dao)
        allUsers = repository.allUsers
    }

   fun addUser(user:LogSigEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun deleteUser(user: LogSigEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(user)
    }

    fun getNamePassword(name:String, password:String) : LiveData<LogSigEntity?> {
        return repository.getNamePassword(name, password)
    }

    fun getPassword(password: String) : LiveData<LogSigEntity?> {
        return repository.getPassword(password)
    }

}