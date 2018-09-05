package com.example.android.androidarchkotlin.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.androidarchkotlin.R
import com.example.android.androidarchkotlin.app
import com.example.android.androidarchkotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app.component.inject(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.hello.observe(this, Observer {
            tv_hello.text = it
        })
    }
}