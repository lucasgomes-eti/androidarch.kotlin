package com.example.android.androidarchkotlin.di

import com.example.android.androidarchkotlin.App
import com.example.android.androidarchkotlin.ui.MainActivity
import com.example.android.androidarchkotlin.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RemoteModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(mainViewModel: MainViewModel)
}