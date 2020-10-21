package com.nik.mycar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.data.CarDao
import com.nik.mycar.data.FuellingDao
import java.lang.IllegalArgumentException

class CarDetailsViewModelFactory(
    private val carDao: CarDao,
    private val fuellingDao: FuellingDao,
    private val carId: String
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
            return CarDetailsViewModel(carDao, fuellingDao, carId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}