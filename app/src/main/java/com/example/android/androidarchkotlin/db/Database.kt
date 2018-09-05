package com.example.android.androidarchkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.android.androidarchkotlin.db.dao.MainDao
import com.example.android.androidarchkotlin.db.model.MainEntity

@Database(entities = [MainEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun mainDao(): MainDao
}