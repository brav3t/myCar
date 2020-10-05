package com.nik.mycar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CarsDao {

    @Transaction // Rooms to queries, Transaction ensures the whole operation is performed atomically
    @Query("SELECT * FROM cars_table")
    fun getCars(): LiveData<List<CarWithRelations>>

    @Insert
    suspend fun insert(car: Car)

    @Query("DELETE FROM cars_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM cars_table")
    suspend fun clear()

    @Query("SELECT * from cars_table WHERE id = :key")
    suspend fun get(key: Long): Car?

    @Query("SELECT * FROM cars_table ORDER BY id DESC")
    fun getAllCheckpoints(): LiveData<List<Car>>

}
