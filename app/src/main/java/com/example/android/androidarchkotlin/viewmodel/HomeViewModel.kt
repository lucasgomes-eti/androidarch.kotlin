package com.example.android.androidarchkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import com.example.android.androidarchkotlin.App
import com.example.android.androidarchkotlin.db.model.MainEntity
import com.example.android.androidarchkotlin.model.api.Result
import com.example.android.androidarchkotlin.repository.MainRepository
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mainRepository: MainRepository

    private var _main = MutableLiveData<MainEntity>()

    val hello: LiveData<String>
    get() = _main.map { it.nazarLocation }

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var liveData: LiveData<Result<MainEntity>>? = null
    private var observer: Observer<Result<MainEntity>>? = null

    init {
        getApplication<App>().component.inject(this)

        loadMain()
    }

    fun loadMain() {
        observer = Observer<Result<MainEntity>> {
            when (it) {
                is Result.Offline -> {
                    _main.postValue(it.data)
                    _status.postValue("Showing offline data\n")
                }
                is Result.Loading -> if (it.isLoading)
                    _status.postValue("Loading start\n")
                else {
                    _status.postValue("Loading finish")

                    observer?.let { o -> liveData?.removeObserver(o) }
                }
                is Result.Success -> {
                    _main.postValue(it.data)
                    _status.postValue("Load success\n")
                }
                is Result.Error -> {
                    _status.postValue("An error ocurred: ${it.exception.message}\n")
                }
            }
        }

        liveData = mainRepository.loadMain()
        liveData!!.observeForever(observer!!)
    }

    override fun onCleared() {
        super.onCleared()
        observer?.let { liveData?.removeObserver(it) }
    }
}