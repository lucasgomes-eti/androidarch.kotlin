package com.example.android.androidarchkotlin.ui

import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.androidarchkotlin.R
import com.example.android.androidarchkotlin.app
import com.example.android.androidarchkotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app.component.inject(this)

        viewModel.hello.observe(this, Observer {
            tv_hello.text = it
        })
    }
}