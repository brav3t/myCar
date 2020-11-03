package com.nik.mycar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nik.mycar.data.Car
import com.nik.mycar.data.CarDao
import kotlinx.coroutines.launch

class CarViewModel (
    private val carDao: CarDao,
) : ViewModel() {

    val cars: LiveData<List<Car>> = carDao.getAll()

    fun addCar(carId: String) {
        viewModelScope.launch {
            val car = Car(carId)
            carDao.insert(car)
        }
    }
}
