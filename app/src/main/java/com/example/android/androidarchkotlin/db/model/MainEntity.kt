package com.example.android.androidarchkotlin.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.android.androidarchkotlin.model.Main

@Entity(tableName = "main")
data class MainEntity(
        @PrimaryKey
        var hello: String
) {
    fun map() = Main(hello)
}