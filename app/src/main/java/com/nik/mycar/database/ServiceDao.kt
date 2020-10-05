package com.nik.mycar.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface ServiceDao {

    @Insert
    suspend fun insert(service: Service)

    @Query("DELETE FROM services_table WHERE serviceId = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM services_table")
    suspend fun clear()

    @Query("SELECT * from services_table WHERE serviceId = :key")
    suspend fun get(key: Long): Service?

    @Query("SELECT * FROM services_table ORDER BY serviceId DESC")
    fun getAllService(): LiveData<List<Service>>

}
