package com.example.android.androidarchkotlin

import android.app.Activity
import android.app.Application
import com.example.android.androidarchkotlin.di.AppComponent
import com.example.android.androidarchkotlin.di.AppModule
import com.example.android.androidarchkotlin.di.DaggerAppComponent
import com.example.android.androidarchkotlin.di.DatabaseModule
import com.example.android.androidarchkotlin.di.RemoteModule

open class App : Application() {
    open val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .databaseModule(DatabaseModule(this))
                .remoteModule(RemoteModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

val Activity.app: App
    get() = application as App