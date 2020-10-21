package com.nik.mycar.viewmodels

import androidx.lifecycle.*
import com.nik.mycar.data.CarDao
import com.nik.mycar.data.Fuelling
import com.nik.mycar.data.FuellingDao
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class CarDetailsViewModel(
    private val carDao: CarDao,
    private val fuellingDao: FuellingDao,
    val carId: String
    ) : ViewModel() {

    val costSum: LiveData<String?> = Transformations.map(fuellingDao.getAllCostByCarId(carId)) { sumOfCosts ->
        NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(sumOfCosts?: 0.0)
    }

    val fuellings: LiveData<List<Fuelling>> = fuellingDao.getAllByCarId(carId)

    fun addFuelling(amount: Double, cost: Double) {
        viewModelScope.launch {
            val fuelling = Fuelling(carId, amount, cost)
            fuellingDao.insert(fuelling)
        }
    }

    fun deleteCar() {
      viewModelScope.launch {
          fuellingDao.deleteCarEntries(carId)
          carDao.deleteCar(carId)
      }
    }
}