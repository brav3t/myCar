package com.nik.mycar.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepo @Inject constructor(private val carDao: CarDao) {

    suspend fun addCar(carID: String) {
        val car = Car(carID)
        carDao.insertCar(car)
    }

    fun getCar(carId: String) = carDao.getCar(carId)

    fun getAllCars() = carDao.getAllCars()

    suspend fun deleteCar(carId: String) = carDao.deleteCar(carId)

    suspend fun deleteAllCars() = carDao.deleteAllCars()
}
