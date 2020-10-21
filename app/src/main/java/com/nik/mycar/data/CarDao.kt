package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * from cars_table WHERE id = :carId")
    fun getCar(carId: String): Car

    @Query("SELECT * FROM cars_table ORDER BY id DESC")
    fun getAllCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Query("DELETE FROM cars_table WHERE id = :carId")
    suspend fun deleteCar(carId: String?)

    @Query("DELETE FROM cars_table")
    suspend fun deleteAllCars()
}
