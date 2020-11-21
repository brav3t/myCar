package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(service: Service)

    @Query("DELETE FROM service_table WHERE car_id = :carId")
    suspend fun deleteAllByCarId(carId: String)

    @Query("SELECT * FROM service_table ORDER BY car_id = :carId DESC")
    fun getAllByCarId(carId: String): LiveData<List<Service>>
}
