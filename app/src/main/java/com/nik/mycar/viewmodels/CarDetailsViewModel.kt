package com.nik.mycar.viewmodels

import androidx.lifecycle.*
import com.nik.mycar.data.CarDao
import com.nik.mycar.data.Fuelling
import com.nik.mycar.data.FuellingDao
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

enum class FuellingOrderBy {
    DATE_DESCENDING,
    DATE_ASCENDING,
    AMOUNT_DESCENDING,
    AMOUNT_ASCENDING,
    COST_DESCENDING,
    COST_ASCENDING
}

class CarDetailsViewModel(
    private val carDao: CarDao,
    private val fuellingDao: FuellingDao,
    val carId: String
    ) : ViewModel() {

    val costSum: LiveData<String?> = Transformations.map(fuellingDao.getAllCostByCarId(carId)) { sumOfCosts ->
        NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(sumOfCosts?: 0.0)
    }

    fun deleteCar() {
        viewModelScope.launch {
            fuellingDao.deleteCarRelatedEntries(carId)
            carDao.delete(carId)
        }
    }

    // FUELLING
    fun addFuelling(amount: Double, cost: Double) {
        viewModelScope.launch {
            val fuelling = Fuelling(carId, amount, cost)
            fuellingDao.insert(fuelling)
        }
    }

    private val fuellingDateDesc: LiveData<List<Fuelling>> = fuellingDao.getByDateDesc(carId)
    private val fuellingDateAsc: LiveData<List<Fuelling>> = fuellingDao.getByDateAsc(carId)
    private val fuellingAmountDesc: LiveData<List<Fuelling>> = fuellingDao.getByAmountDesc(carId)
    private val fuellingAmountAsc: LiveData<List<Fuelling>> = fuellingDao.getByAmountAsc(carId)
    private val fuellingCostDesc: LiveData<List<Fuelling>> = fuellingDao.getByCostDesc(carId)
    private val fuellingCostAsc: LiveData<List<Fuelling>> = fuellingDao.getByCostAsc(carId)

    val fuelling = MediatorLiveData<List<Fuelling>>()
    var currentOrder = FuellingOrderBy.DATE_DESCENDING

    init {
        fuelling.addSource(fuellingDateDesc) {
            if (currentOrder == FuellingOrderBy.DATE_DESCENDING) fuelling.value = it
        }
        fuelling.addSource(fuellingDateAsc) {
            if (currentOrder == FuellingOrderBy.DATE_ASCENDING) fuelling.value = it
        }
        fuelling.addSource(fuellingAmountDesc) {
            if (currentOrder == FuellingOrderBy.AMOUNT_DESCENDING) fuelling.value = it
        }
        fuelling.addSource(fuellingAmountAsc) {
            if (currentOrder == FuellingOrderBy.AMOUNT_ASCENDING) fuelling.value = it
        }
        fuelling.addSource(fuellingCostDesc) {
            if (currentOrder == FuellingOrderBy.COST_DESCENDING) fuelling.value = it
        }
        fuelling.addSource(fuellingCostAsc) {
            if (currentOrder == FuellingOrderBy.COST_ASCENDING) fuelling.value = it
        }
    }

    fun flipDateOrder() = when (currentOrder) {
        FuellingOrderBy.DATE_DESCENDING -> fuellingDateAsc.value?.let { fuelling.value = fuelling.value?.asReversed() }.also { currentOrder = FuellingOrderBy.DATE_ASCENDING }
        FuellingOrderBy.DATE_ASCENDING -> fuellingDateDesc.value?.let { fuelling.value = fuelling.value?.asReversed() }.also { currentOrder = FuellingOrderBy.DATE_DESCENDING }
        else -> fuellingDateDesc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.DATE_DESCENDING }
    }

    fun flipAmountOrder() = when (currentOrder) {
        FuellingOrderBy.AMOUNT_DESCENDING -> fuellingAmountAsc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.AMOUNT_ASCENDING }
        FuellingOrderBy.AMOUNT_ASCENDING -> fuellingAmountDesc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.AMOUNT_DESCENDING }
        else -> fuellingAmountDesc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.AMOUNT_DESCENDING }
    }

    fun flipCostOrder() = when (currentOrder) {
        FuellingOrderBy.COST_DESCENDING -> fuellingCostAsc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.DATE_ASCENDING }
        FuellingOrderBy.COST_ASCENDING -> fuellingCostDesc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.COST_DESCENDING }
        else -> fuellingCostDesc.value?.let { fuelling.value = it }.also { currentOrder = FuellingOrderBy.COST_DESCENDING }
    }

}
