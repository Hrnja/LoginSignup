package com.example.loginsignup.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Users")
class LogSigEntity (
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="lastname") val lastname:String,
    @ColumnInfo(name="email") val email:String,
    @ColumnInfo(name="password")  @PrimaryKey(autoGenerate = false) val password:String
    )
{}