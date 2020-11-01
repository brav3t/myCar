package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuellingDao {

    @Insert
    suspend fun insert(fuelling: Fuelling)

    @Query("DELETE FROM fuelling_table WHERE car_id = :carId")
    suspend fun deleteCarEntries(carId: String)

    @Query("DELETE FROM fuelling_table")
    suspend fun clear()

    @Query("SELECT SUM(cost) AS allCost FROM fuelling_table WHERE car_id = :carId")
    fun getAllCostByCarId(carId: String): LiveData<Double>

    // Order by
    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY date DESC")
    fun getByDateDesc(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY date ASC")
    fun getByDateAsc(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY amount DESC")
    fun getByAmountDesc(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY amount ASC")
    fun getByAmountAsc(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY cost DESC")
    fun getByCostDesc(carId: String): LiveData<List<Fuelling>>

    @Query("SELECT * FROM fuelling_table WHERE car_id = :carId ORDER BY cost ASC")
    fun getByCostAsc(carId: String): LiveData<List<Fuelling>>

}
