package com.example.android.androidarchkotlin.model.api

sealed class Result<out R> {
    data class Offline<out T>(val data: T) : Result<T>()
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}