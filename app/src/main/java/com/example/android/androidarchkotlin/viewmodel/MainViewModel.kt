package com.example.android.androidarchkotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.android.androidarchkotlin.App
import com.example.android.androidarchkotlin.R
import com.example.android.androidarchkotlin.repository.MainRepository
import javax.inject.Inject

class MainViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var mainRepository: MainRepository

    private val _hello = MutableLiveData<String>()
    val hello: LiveData<String>
        get() = _hello

    init {
        getApplication<App>().component.inject(this)
        _hello.postValue(application.getString(R.string.app_name))
    }
}