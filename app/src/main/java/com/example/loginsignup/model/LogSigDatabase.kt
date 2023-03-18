package com.example.loginsignup.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LogSigEntity::class), version = 1, exportSchema = false)
abstract class LogSigDatabase:RoomDatabase() {
    abstract fun getDao(): LogSigDao

    companion object {
        @Volatile
        private var INSTANCE : LogSigDatabase? = null
        fun getDatabase(context: Context) : LogSigDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogSigDatabase::class.java,
                    "Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}