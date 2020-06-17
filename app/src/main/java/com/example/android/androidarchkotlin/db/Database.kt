package com.example.android.androidarchkotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.androidarchkotlin.db.dao.MainDao
import com.example.android.androidarchkotlin.db.model.MainEntity

@Database(entities = [MainEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun mainDao(): MainDao
}