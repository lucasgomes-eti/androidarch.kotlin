package com.example.android.androidarchkotlin.remote.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.android.androidarchkotlin.AppExecutors
import com.example.android.androidarchkotlin.model.api.Resource

abstract class NetworkBoundResource<ReturnType, DBType>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ReturnType>>()

    init {
        initialize()
    }

    @MainThread
    private fun initialize() {
        setValue(Resource.loading(null, null))
        val dbSource = loadFromDb()
        if (dbSource == null)
            throw IllegalArgumentException("DbSource is null")
        result.addSource<DBType>(dbSource) { data ->
            result.removeSource<DBType>(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource<DBType>(dbSource) { setValue(Resource.success(map(it)))}
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ReturnType>) {
        if (result.value == null || result.value!! != newValue) {
            result.postValue(newValue)
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<DBType>?) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        if (dbSource == null)
            throw IllegalArgumentException("DbSource is null")
        if (apiResponse == null)
            throw IllegalArgumentException("ApiResponse is null")
        result.addSource<DBType>(dbSource) { newData -> setValue(Resource.loading(map(newData), null)) }
        result.addSource<ApiResponse<ReturnType>>(apiResponse) { response ->
            result.removeSource<ApiResponse<ReturnType>>(apiResponse)
            result.removeSource<DBType>(dbSource)
            response?.let {
                if (it.isSuccessful) {
                    appExecutors.diskIO.execute {
                        processResponse(it)?.let { saveCallResult(it) }
                        appExecutors.mainThread.execute {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource<DBType>(loadFromDb()) { newData ->
                                setValue(Resource.success(map(newData)))
                            }
                        }
                    }
                } else {
                    onFetchFailed(it.code)
                    result.addSource<DBType>(dbSource) { newData ->
                        setValue(Resource.error(map(newData), it.errorMessage, it.code))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ReturnType>> = result

    @MainThread
    abstract fun loadFromDb(): LiveData<DBType>

    @MainThread
    abstract fun shouldFetch(data: DBType?): Boolean

    @MainThread
    abstract fun createCall(): LiveData<ApiResponse<ReturnType>>?

    @WorkerThread
    protected fun processResponse(response: ApiResponse<ReturnType>) = response.body

    abstract fun map(data: DBType?): ReturnType?

    @MainThread
    protected open fun onFetchFailed(errorCode: Int) {}

    @WorkerThread
    abstract fun saveCallResult(item: ReturnType)
}