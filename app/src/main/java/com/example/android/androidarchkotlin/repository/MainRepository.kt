package com.example.android.androidarchkotlin.repository

import androidx.lifecycle.LiveData
import com.example.android.androidarchkotlin.AppExecutors
import com.example.android.androidarchkotlin.db.dao.MainDao
import com.example.android.androidarchkotlin.model.Main
import com.example.android.androidarchkotlin.model.api.Resource
import com.example.android.androidarchkotlin.remote.MainService
import com.example.android.androidarchkotlin.remote.util.ApiResponse
import com.example.android.androidarchkotlin.remote.util.NetworkResource
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val mainService: MainService,
        private val mainDao: MainDao,
        private val appExecutors: AppExecutors) {

    fun loadMain(): LiveData<Resource<Main>> {
        return object : NetworkResource<Main>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<Main>> {
                return mainService.getMain()
            }
        }.asLiveData()
    }
}