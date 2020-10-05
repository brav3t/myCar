package com.nik.mycar.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface CheckpointsDao {

        @Insert
        suspend fun insert(checkpoint: Checkpoint)

        @Query("DELETE FROM checkpoints_table WHERE checkpointId = :key")
        suspend fun delete(key: Long)

        @Query("DELETE FROM checkpoints_table")
        suspend fun clear()

        @Query("SELECT * from checkpoints_table WHERE checkpointId = :key")
        suspend fun get(key: Long): Checkpoint?

        @Query("SELECT * FROM checkpoints_table ORDER BY checkpointId DESC")
        fun getAllCheckpoints(): LiveData<List<Checkpoint>>

}
