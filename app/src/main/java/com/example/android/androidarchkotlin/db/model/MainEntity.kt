package com.example.android.androidarchkotlin.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.androidarchkotlin.model.Main

@Entity(tableName = "main")
data class MainEntity(
        @PrimaryKey
        var hello: String
) {
    fun map() = Main(hello)
}