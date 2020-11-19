package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FuellingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fuelling: Fuelling)

    @Query("DELETE FROM fuelling_table WHERE car_id = :carId")
    suspend fun deleteAllByCarId(carId: String)

    @Query("SELECT SUM(cost) AS allCost FROM fuelling_table WHERE car_id = :carId")
    fun getSumOfCostsByCarId(carId: String): LiveData<Double>

    @Query(
        "SELECT * FROM fuelling_table " +
                "WHERE car_id = :carId " +
                "AND amount >= :minAmount AND amount <= :maxAmount " +
                "AND cost >= :minCost AND cost <= :maxCost " +
                "ORDER BY date DESC "
    )
    fun getAll(
        carId: String,
        minAmount: Double,
        maxAmount: Double,
        minCost: Double,
        maxCost: Double
    ): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table ORDER BY date DESC")
    fun getAll(): LiveData<List<Fuelling>>

}
