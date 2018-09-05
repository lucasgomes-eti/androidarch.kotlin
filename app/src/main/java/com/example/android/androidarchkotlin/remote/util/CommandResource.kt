package com.example.android.androidarchkotlin.remote.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.android.androidarchkotlin.AppExecutors
import com.example.android.androidarchkotlin.model.api.Resource

abstract class CommandResource<T>(private val appExecutors: AppExecutors){
    private val result = MediatorLiveData<Resource<T>>()

    init {
        initialize()
    }

    @MainThread
    private fun initialize(){
        result.value = Resource.loading(null, null)
        executeCommand()
    }

    private fun executeCommand() {
        val apiCall = createCommandCall()
                ?: throw IllegalArgumentException("ApiResponse is null")
        result.addSource(apiCall){
            result.removeSource(apiCall)
            it?.let {
                if (it.isSuccessful) {
                    appExecutors.diskIO.execute {
                        processResponse(it)?.let { saveResult(it) }
                        result.postValue(Resource.success(it.body))
                    }
                } else {
                    onCommandFailed(it.code)
                    result.postValue(Resource.error(null, it.errorMessage, it.code))
                }
            }
        }
    }

    @MainThread
    protected open fun onCommandFailed(errorCode: Int) {}

    @WorkerThread
    protected abstract fun saveResult(item: T)

    @MainThread
    abstract fun createCommandCall(): LiveData<ApiResponse<T>>?

    @WorkerThread
    private fun processResponse(response: ApiResponse<T>) = response.body

    fun asLiveData(): LiveData<Resource<T>> = result
}