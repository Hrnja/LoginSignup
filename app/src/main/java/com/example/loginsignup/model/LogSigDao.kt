package com.example.loginsignup.model


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LogSigDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user:LogSigEntity)

    @Delete
    suspend fun delete(user:LogSigEntity)

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers() : LiveData<List<LogSigEntity>>

    @Query("SELECT * FROM users WHERE name=:name AND password=:password")
    fun getNamePassword(name:String, password:String) : LiveData<LogSigEntity?>

    @Query("SELECT * FROM users WHERE password=:password")
    fun getPassword(password: String) : LiveData<LogSigEntity?>

}