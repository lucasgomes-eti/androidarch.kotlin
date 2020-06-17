package com.example.android.androidarchkotlin

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutors(val diskIO: Executor, val networkIO: Executor, val mainThread: Executor) {

    @Inject
    constructor() : this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
            MainThreadExecutor())

    @VisibleForTesting
    class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}