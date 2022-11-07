package com.example.tddcounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Count::class], version = 1)
abstract class CountDatabase : RoomDatabase() {
    abstract fun countDao(): CountDAO

}