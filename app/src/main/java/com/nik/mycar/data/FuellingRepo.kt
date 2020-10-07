package com.nik.mycar.data

import androidx.lifecycle.LiveData

class FuellingRepo(private val fuellingDao: FuellingDao) {

    val allFuelling: LiveData<List<Fuelling>> = fuellingDao.getAllFuelling()

    suspend fun getFuelling(key: Long) {
        fuellingDao.getFuelling(key)
    }

    suspend fun insert(fuelling: Fuelling) {
        fuellingDao.insert(fuelling)
    }

    suspend fun delete(key: Long) {
        fuellingDao.delete(key)
    }

    suspend fun clear() {
        fuellingDao.clear()
    }

}
