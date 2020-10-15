package com.nik.mycar.viewmodels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nik.mycar.data.Car
import com.nik.mycar.data.CarRepo
import kotlinx.coroutines.launch

class CarListVM @ViewModelInject constructor(
    private val carRepo: CarRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val cars: LiveData<List<Car>> = carRepo.getAllCars()

    fun addCar(carId: String) {
        viewModelScope.launch {
            carRepo.addCar(carId)
        }
    }
}