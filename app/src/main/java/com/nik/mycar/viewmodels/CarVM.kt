package com.nik.mycar.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nik.mycar.data.Car
import com.nik.mycar.data.CarRepo
import kotlinx.coroutines.launch

class CarVM @ViewModelInject constructor(
    private val carRepo: CarRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var carId: String? = null

    val cars: LiveData<List<Car>> = carRepo.getAllCars()

    fun addCar(carId: String) {
        viewModelScope.launch {
            carRepo.addCar(carId)
        }
    }
}
