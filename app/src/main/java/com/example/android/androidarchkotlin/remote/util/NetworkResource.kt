package com.example.android.androidarchkotlin.remote.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.android.androidarchkotlin.AppExecutors
import com.example.android.androidarchkotlin.model.api.Resource

abstract class NetworkResource<T> (private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<T>>()

    init {
        initialize()
    }

    @MainThread
    private fun initialize() {
        result.value = Resource.loading(null, null)

        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
                ?: throw IllegalArgumentException("ApiResponse is null")
        result.addSource<ApiResponse<T>>(apiResponse) {
            result.removeSource<ApiResponse<T>>(apiResponse)
            it?.let {
                if (it.isSuccessful) {
                    appExecutors.mainThread.execute {
                        setValue(Resource.success(processResponse(it)))
                    }
                } else {
                    onFetchFailed(it.code)
                    setValue(Resource.error(it.body, it.errorMessage, it.code))
                }
            }
        }

        val progressSource = getProgressLiveData()
        progressSource?.let {
            result.addSource(it) { progress ->
                progress?.let { setValue(Resource.loading(null, it)) }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<T>> = result

    @MainThread
    abstract fun createCall(): LiveData<ApiResponse<T>>?

    open fun getProgressLiveData() : LiveData<Int>? = null

    @WorkerThread
    private fun processResponse(response: ApiResponse<T>) = response.body

    @MainThread
    protected open fun onFetchFailed(errorCode: Int) {}

    @MainThread
    private fun setValue(newValue: Resource<T>){
        if (result.value == null || result.value != newValue){
            result.value = newValue
        }
    }
}