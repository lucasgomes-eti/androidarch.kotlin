package com.example.android.androidarchkotlin

import android.app.Activity
import android.app.Application
import com.example.android.androidarchkotlin.di.*

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