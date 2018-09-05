package com.example.android.androidarchkotlin.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.example.android.androidarchkotlin.db.model.MainEntity

@Dao
interface MainDao {
    @Insert(onConflict = REPLACE)
    fun createMain(mainEntity: MainEntity)

    @Query("SELECT * FROM main")
    fun readMain(): LiveData<List<MainEntity>>

    @Update(onConflict = REPLACE)
    fun updateMain(mainEntity: MainEntity)

    @Delete
    fun deleteMain(mainEntity: MainEntity)
}