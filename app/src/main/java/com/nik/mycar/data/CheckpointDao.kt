package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CheckpointDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(checkpoint: Checkpoint)

        @Query("DELETE FROM checkpoints_table WHERE car_id = :carID")
        suspend fun deleteAllByCarId(carID: String)

        @Query("SELECT * from checkpoints_table WHERE car_id = :carId ORDER BY date DESC")
        fun getAllByCarId(carId: String): LiveData<List<Checkpoint>>

        @Query("SELECT checkpoint FROM checkpoints_table WHERE car_id = :carId ORDER BY date ASC LIMIT 1")
        fun getLast(carId: String): LiveData<Int>
}
