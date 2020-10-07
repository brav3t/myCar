package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarsDao {

    @Insert
    suspend fun insert(car: Car)

    @Query("DELETE FROM cars_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM cars_table")
    suspend fun clear()

    @Query("SELECT * from cars_table WHERE id = :key")
    suspend fun getCar(key: Long): Car?

    @Query("SELECT * FROM cars_table ORDER BY id DESC")
    fun getAllCars(): LiveData<List<Car>>

}
