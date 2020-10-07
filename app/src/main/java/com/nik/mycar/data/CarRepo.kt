package com.nik.mycar.data

import androidx.lifecycle.LiveData

class CarRepo(private val carsDao: CarsDao) {

    val allCars: LiveData<List<Car>> = carsDao.getAllCars()

    suspend fun getCar(key: Long) {
        carsDao.getCar(key)
    }

    suspend fun insert(car: Car) {
        carsDao.insert(car)
    }

    suspend fun delete(key: Long) {
        carsDao.delete(key)
    }

    suspend fun clear() {
        carsDao.clear()
    }
}
