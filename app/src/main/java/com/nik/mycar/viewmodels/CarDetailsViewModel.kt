package com.nik.mycar.viewmodels

import androidx.lifecycle.*
import com.nik.mycar.data.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.math.absoluteValue

class CarDetailsViewModel(
    private val carDao: CarDao,
    private val fuellingDao: FuellingDao,
    private val checkpointDao: CheckpointDao,
    val carId: String
    ) : ViewModel() {

    fun deleteCar() {
        viewModelScope.launch {
            fuellingDao.deleteAllByCarId(carId)
            carDao.delete(carId)
        }
    }

    val fuellingCostSum: LiveData<String> = Transformations.map(fuellingDao.getSumOfCostsByCarId(carId)) { sumOfCosts ->
        NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(sumOfCosts?: 0.0)
    }

    fun addFuelling(amount: Double, cost: Double) {
        viewModelScope.launch {
            val fuellingItem = Fuelling(carId, amount, cost)
            fuellingDao.insert(fuellingItem)
        }
    }

    fun deleteFuellingList() {
        viewModelScope.launch {
            fuellingDao.deleteAllByCarId(carId)
        }
    }

    fun addCheckpoint(checkpoint: Int) {
        viewModelScope.launch {
            val checkpointItem = Checkpoint(carId, checkpoint)
            checkpointDao.insert(checkpointItem)
        }
    }

    fun deleteCheckpointList() {
        viewModelScope.launch {
            checkpointDao.deleteAllByCarId(carId)
        }
    }

    val lastCheckpoint: LiveData<Int> = checkpointDao.getLast(carId)
    val lastCheckpointStr: LiveData<String> =  Transformations.map(lastCheckpoint){ checkpoint ->
        (checkpoint?.toString() ?: "0") + " Km"
    }

    val serviceCost: String = NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(0.0)

    private var fuellingListSource = fuellingDao.getAll(carId, 0.0, Double.MAX_VALUE, 0.0, Double.MAX_VALUE)
    val fuellingList = MediatorLiveData<List<Fuelling>>()

    private var checkpointListSource = checkpointDao.getAllByCarId(carId)
    val checkpointList = MediatorLiveData<List<Checkpoint>>()

    init {
        fuellingList.addSource(fuellingListSource) {
            fuellingList.value = it
        }

        checkpointList.addSource(checkpointListSource) {
            checkpointList.value = it
        }
    }

    fun reloadFuellingList(minAmount: Double, maxAmount: Double, minCost: Double, maxCost: Double) {
        fuellingList.removeSource(fuellingListSource)
        fuellingListSource = fuellingDao.getAll(carId, minAmount, maxAmount, minCost, maxCost)
        fuellingList.addSource(fuellingListSource) {
            fuellingList.value = it
        }
    }
}
