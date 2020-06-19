package com.example.android.androidarchkotlin.remote

import com.example.android.androidarchkotlin.model.Main
import retrofit2.Response
import retrofit2.http.GET

interface MainService {
    @GET("/location/current")
    suspend fun getMain(): Response<Main>
}