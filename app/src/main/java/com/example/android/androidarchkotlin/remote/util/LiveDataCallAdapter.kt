package com.example.android.androidarchkotlin.remote.util

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R> (private val responseType: Type)
    : CallAdapter<R, LiveData<ApiResponse<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>?): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)){
                    call?.enqueue(object : Callback<R> {
                        override fun onFailure(call: Call<R>?, t: Throwable) {
                            postValue(ApiResponse(t))
                        }

                        override fun onResponse(call: Call<R>?, response: Response<R>) {
                            postValue(ApiResponse(response))
                        }
                    })
                }
            }
        }
    }
}