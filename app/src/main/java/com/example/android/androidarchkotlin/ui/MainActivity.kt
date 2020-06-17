package com.example.android.androidarchkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.androidarchkotlin.R
import com.example.android.androidarchkotlin.app

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app.component.inject(this)
    }
}