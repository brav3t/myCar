package com.nik.mycar.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nik.mycar.data.CarRepo
import kotlinx.coroutines.launch

class CarDetailsVM @ViewModelInject constructor(
    private val carRepo: CarRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var carId: String? = null

    fun deleteCar() {
        viewModelScope.launch {
            carRepo.deleteCar(carId)
        }
    }
}
