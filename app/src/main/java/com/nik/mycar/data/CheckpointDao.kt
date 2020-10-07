package com.nik.mycar.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface CheckpointDao {

        @Insert
        suspend fun insert(checkpoint: Checkpoint)

        @Query("DELETE FROM checkpoints_table WHERE id = :key")
        suspend fun delete(key: Long)

        @Query("DELETE FROM checkpoints_table")
        suspend fun clear()

        @Query("SELECT * from checkpoints_table WHERE id = :key")
        suspend fun getCheckpoint(key: Long): Checkpoint?

        @Query("SELECT * FROM checkpoints_table ORDER BY id DESC")
        fun getAllCheckpoints(): LiveData<List<Checkpoint>>

}
