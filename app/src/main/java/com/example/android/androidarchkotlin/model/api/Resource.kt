package com.example.android.androidarchkotlin.model.api

data class Resource<out T>(
        val status: Status,
        val data: T?,
        val message: String?,
        val percentage: Int?,
        val errorCode: Int? = 0
) {
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null, null)
        fun <T> error(data: T?, msg: String?, errorCode: Int? = 0) = Resource(Status.ERROR, data, msg, null, errorCode)
        fun <T> loading(data: T?, percentage: Int? = null) = Resource(Status.LOADING, data, null, percentage)
    }
}