package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: Car)

    @Query("DELETE FROM cars_table WHERE id = :carId")
    suspend fun delete(carId: String?)

    @Query("SELECT * FROM cars_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Car>>
}
