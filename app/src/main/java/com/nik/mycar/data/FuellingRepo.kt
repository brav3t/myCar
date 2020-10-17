package com.nik.mycar.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FuellingRepo @Inject constructor(private val fuellingDao: FuellingDao) {

    suspend fun insert(carId: String, amount: Double, cost: Double) {
        val fuelling = Fuelling(carId, amount, cost)
        fuellingDao.insert(fuelling)
    }

    fun getAllCostByCarId(carId: String) = fuellingDao.getAllCostByCarId(carId)

    fun getAllByCarId(carId: String) = fuellingDao.getAllByCarId(carId)

    suspend fun clear() = fuellingDao.clear()
}
