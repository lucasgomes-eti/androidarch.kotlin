package com.example.android.androidarchkotlin.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.android.androidarchkotlin.db.Database
import com.example.android.androidarchkotlin.db.dao.MainDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideDatabase(): Database {
        return Room
                .databaseBuilder(context, Database::class.java, "androidarchjava.db")
                .build()
    }

    @Provides
    @Singleton
    internal fun providesMainDao(database: Database): MainDao {
        return database.mainDao()
    }
}