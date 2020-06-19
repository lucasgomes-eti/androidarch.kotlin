package com.example.android.androidarchkotlin.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main")
data class MainEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var nazarLocation: String
)