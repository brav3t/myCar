package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface FuellingDao {

    @Insert
    suspend fun insert(fuelling: Fuelling)

    @Query("DELETE FROM fuelling_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM fuelling_table")
    suspend fun clear()

    @Query("SELECT * from fuelling_table WHERE id = :key")
    suspend fun getFuelling(key: Long): Fuelling?

    @Query("SELECT * FROM fuelling_table ORDER BY id DESC")
    fun getAllFuelling(): LiveData<List<Fuelling>>

}
