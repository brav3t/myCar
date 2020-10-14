package com.nik.mycar.viewmodels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nik.mycar.data.Car
import com.nik.mycar.data.CarRepo
import kotlinx.coroutines.launch

class CarViewModel @ViewModelInject constructor(
    private val carRepo: CarRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val cars: LiveData<List<Car>> = carRepo.getAllCars()

    fun addCar(carId: String) {
        viewModelScope.launch {
            carRepo.addCar(carId)
        }
    }

    init {
        Log.i("CarViewModel", "CarViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CarViewModel", "CarViewModel destroyed!")
    }
}