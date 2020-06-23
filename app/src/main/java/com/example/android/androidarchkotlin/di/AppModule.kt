package com.example.android.androidarchkotlin.di

import android.content.Context.MODE_PRIVATE
import com.example.android.androidarchkotlin.App
import dagger.Module
import dagger.Provides
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
    fun provideSharedPreferences() = app.getSharedPreferences("defaultSharedPreferences", MODE_PRIVATE)
}