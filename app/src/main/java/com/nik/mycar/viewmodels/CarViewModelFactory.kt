package com.nik.mycar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.data.CarDao
import java.lang.IllegalArgumentException

class CarViewModelFactory(
    private val carDao: CarDao
) :ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarViewModel::class.java)) {
            return CarViewModel(carDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}