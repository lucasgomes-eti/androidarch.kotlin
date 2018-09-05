package com.example.android.androidarchkotlin.model

import com.example.android.androidarchkotlin.db.model.MainEntity

data class Main (
        val hello: String
) {
    fun  map() = MainEntity(hello)
}