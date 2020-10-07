package com.nik.mycar.data

import androidx.lifecycle.LiveData

class CheckpointRepo(private val checkpointsDao: CheckpointDao) {

    val allCheckpoints: LiveData<List<Checkpoint>> = checkpointsDao.getAllCheckpoints()

    suspend fun getCheckpoint(key: Long) {
        checkpointsDao.getCheckpoint(key)
    }

    suspend fun insert(checkpoint: Checkpoint) {
        checkpointsDao.insert(checkpoint)
    }

    suspend fun delete(key: Long) {
        checkpointsDao.delete(key)
    }

    suspend fun clear() {
        checkpointsDao.clear()
    }
}
