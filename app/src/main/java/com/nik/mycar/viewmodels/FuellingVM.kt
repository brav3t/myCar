package com.nik.mycar.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nik.mycar.data.CarRepo
import com.nik.mycar.data.FuellingRepo
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class FuellingVM @AssistedInject constructor(
    private val carRepo: CarRepo,
    private val fuellingRepo: FuellingRepo,
    @Assisted private val carId: String
) : ViewModel() {

    val sumOfCosts: Double = fuellingRepo.getAllCostByCarId(carId)

    fun insertFuelling(carId: String, amount: Double, cost: Double) {
        viewModelScope.launch {
            fuellingRepo.insert(carId, amount, cost)
        }
    }

    fun deleteCar() {
        viewModelScope.launch {
            carRepo.deleteCar(carId)
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(carId: String): FuellingVM
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            carId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(carId) as T
            }
        }
    }
}
