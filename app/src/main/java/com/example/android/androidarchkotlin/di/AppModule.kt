package com.example.android.androidarchkotlin.di

import com.example.android.androidarchkotlin.App
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    fun provideContext() = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences() = app.defaultSharedPreferences
}