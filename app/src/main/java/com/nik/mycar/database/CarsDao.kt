package com.nik.mycar.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CarsDao {

    @Transaction
    @Query("SELECT * FROM cars_table")
    fun getCars(): List<CarWithRelations>
}