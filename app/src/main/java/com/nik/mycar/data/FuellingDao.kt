package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuellingDao {

    @Insert
    suspend fun insert(fuelling: Fuelling)

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY date DESC")
    fun getAllByCarId(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT SUM(cost) AS allCost FROM fuelling_table WHERE car_id = :carId")
    fun getAllCostByCarId(carId: String): LiveData<Double>

    @Query("DELETE FROM fuelling_table WHERE car_id = :carId")
    suspend fun deleteCarEntries(carId: String)

    @Query("DELETE FROM fuelling_table")
    suspend fun clear()
}
