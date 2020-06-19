package com.example.android.androidarchkotlin.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.android.androidarchkotlin.db.model.MainEntity

@Dao
interface MainDao {

    @Insert(onConflict = REPLACE)
    suspend fun createMain(mainEntity: MainEntity)

    @Query("SELECT id, nazarLocation FROM main ORDER BY id DESC LIMIT 1")
    suspend fun readMain(): MainEntity?

    @Update(onConflict = REPLACE)
    suspend fun updateMain(mainEntity: MainEntity)

    @Delete
    suspend fun deleteMain(mainEntity: MainEntity)
}