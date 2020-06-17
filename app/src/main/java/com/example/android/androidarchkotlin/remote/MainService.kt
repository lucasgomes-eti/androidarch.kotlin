package com.example.android.androidarchkotlin.remote

import androidx.lifecycle.LiveData
import com.example.android.androidarchkotlin.model.Main
import com.example.android.androidarchkotlin.remote.util.ApiResponse
import retrofit2.http.GET

interface MainService {

    @GET("main")
    fun  getMain(): LiveData<ApiResponse<Main>>
}