package com.nik.mycar.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class) // Tells JUnit that this test are instrumental tests
@SmallTest // Tells JUnit that these tests are unit tests -- If UI tests or Inst. tests then it would be @LargeTests
class FuellingDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var fuellingDao: FuellingDao
    private lateinit var carDao: CarDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        fuellingDao = database.fuellingDao()
        carDao = database.carDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertFuellingItemTest() = runBlockingTest {
        val car = Car("car")
        carDao.insert(car)
        val fuellingItem = Fuelling("car", 10.0, 10000.0, -1)
        fuellingDao.insert(fuellingItem)

        val allCars = fuellingDao.getAll().getOrAwaitValue()
        Truth.assertThat(allCars).contains(fuellingItem)
    }

    @Test
    fun deleteAllFuellingByCarIdTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)
        fuellingDao.deleteAllByCarId(carItem.carId)

        val allCars = fuellingDao.getAll().getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(fuellingItem1)
        Truth.assertThat(allCars).doesNotContain(fuellingItem2)
    }

    @Test
    fun getSumOfCostsByCarIdTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)
        val sum = fuellingDao.getSumOfCostsByCarId(carItem.carId).getOrAwaitValue()

        val allCars = fuellingDao.getAll().getOrAwaitValue()
        Truth.assertThat(allCars.sumOf { fuelling -> fuelling.cost }).isEqualTo(sum)
    }

    @Test
    fun getAllByMinAmountTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)

        val allCars = fuellingDao.getAll(carItem.carId, 20.0, 20.0, 10000.0, 20000.0).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(fuellingItem1)
    }

    @Test
    fun getAllByMaxAmountTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)

        val allCars = fuellingDao.getAll(carItem.carId, 10.0, 10.0, 10000.0, 20000.0).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(fuellingItem2)
    }

    @Test
    fun getAllByMinCostTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)

        val allCars = fuellingDao.getAll(carItem.carId, 10.0, 20.0, 20000.0, 20000.0).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(fuellingItem1)
    }

    @Test
    fun getAllByMaxCostTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val fuellingItem1 = Fuelling("car", 10.0, 10000.0, -1)
        val fuellingItem2 = Fuelling("car", 20.0, 20000.0, 0)
        fuellingDao.insert(fuellingItem1)
        fuellingDao.insert(fuellingItem2)

        val allCars = fuellingDao.getAll(carItem.carId, 10.0, 20.0, 10000.0, 10000.0).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(fuellingItem2)
    }
}