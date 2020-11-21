package com.nik.mycar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.data.CarDao
import com.nik.mycar.data.CheckpointDao
import com.nik.mycar.data.FuellingDao
import com.nik.mycar.data.ServiceDao
import java.lang.IllegalArgumentException

class CarDetailsViewModelFactory(
    private val carDao: CarDao,
    private val fuellingDao: FuellingDao,
    private val serviceDao: ServiceDao,
    private val checkpointDao: CheckpointDao,
    private val carId: String
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
            return CarDetailsViewModel(carDao, fuellingDao, serviceDao, checkpointDao, carId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}