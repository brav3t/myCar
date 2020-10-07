package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DatabaseDao {

    @Transaction // Rooms to queries, Transaction ensures the whole operation is performed atomically
    @Query("SELECT * FROM cars_table")
    fun getAllData(): LiveData<List<CarWithRelations>>
}
