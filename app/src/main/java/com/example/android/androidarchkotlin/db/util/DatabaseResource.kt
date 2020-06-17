package com.example.android.androidarchkotlin.db.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.annotation.MainThread
import com.example.android.androidarchkotlin.AppExecutors
import com.example.android.androidarchkotlin.model.api.Resource

abstract class DatabaseResource<T>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<T>>()

    init {
        initialize()
    }

    @MainThread
    private fun initialize() {
        setValue(Resource.loading(null, null))
        val dbSource = loadFromDb()
        result.addSource<T>(dbSource) { newData -> setValue(Resource.success(newData)) }
    }

    @MainThread
    private fun setValue(newValue: Resource<T>) {
        if (result.value == null || result.value!! != newValue) {
            result.postValue(newValue)
        }
    }

    fun asLiveData(): LiveData<Resource<T>> = result

    @MainThread
    protected abstract fun loadFromDb(): LiveData<T>
}