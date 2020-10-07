package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface ServiceDao {

    @Insert
    suspend fun insert(service: Service)

    @Query("DELETE FROM services_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM services_table")
    suspend fun clear()

    @Query("SELECT * from services_table WHERE id = :key")
    suspend fun getService(key: Long): Service?

    @Query("SELECT * FROM services_table ORDER BY id DESC")
    fun getAllService(): LiveData<List<Service>>

}
