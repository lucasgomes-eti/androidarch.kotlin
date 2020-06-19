package com.example.android.androidarchkotlin.repository

import androidx.lifecycle.liveData
import com.example.android.androidarchkotlin.db.dao.MainDao
import com.example.android.androidarchkotlin.model.api.Result
import com.example.android.androidarchkotlin.remote.MainService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainService: MainService,
    private val mainDao: MainDao
) {

    fun loadMain() = liveData {
        withContext(Dispatchers.IO) {
            /**
             * before call this function you may want to control if you want to fetch new data or not
             */
            suspend fun fetchAndReload() {
                try {
                    val fromApi = mainService.getMain() // fetch data
                    if (fromApi.isSuccessful) { // network call is successful
                        fromApi.body()?.toEntity()?.let { mainDao.createMain(it) } // save to disk
                        val fromDbReloaded = mainDao.readMain() // reload
                        emit(Result.Success(fromDbReloaded!!)) // dispatch reloaded
                    } else { // network throw an error
                        emit(
                            Result.Error(
                                Exception(
                                    fromApi.errorBody()?.toString() ?: "Unknown Error"
                                )
                            )
                        )
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }

            val fromDb = mainDao.readMain() // observe disk
            if (fromDb == null) { // disk is null
                emit(Result.Loading(true)) // dispatch loading info
                fetchAndReload() // fetch and reload data
                emit(Result.Loading(false)) // dispatch loading info
            } else { // disk has data
                emit(Result.Offline(fromDb)) // dispatch old data while reloading
                emit(Result.Loading(true)) // dispatch loading info
                fetchAndReload() // fetch and reload data
                emit(Result.Loading(false)) // dispatch loading info
            }
        }
    }
}