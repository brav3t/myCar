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
class ServiceDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var serviceDao: ServiceDao
    private lateinit var carDao: CarDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        serviceDao = database.serviceDao()
        carDao = database.carDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertServiceItemTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val serviceItem = Service(carItem.carId, "description", 1000.0, -1)
        serviceDao.insert(serviceItem)

        val allCars = serviceDao.getAllByCarId(carItem.carId).getOrAwaitValue()
        Truth.assertThat(allCars).contains(serviceItem)
    }

    @Test
    fun deleteAllServiceByCarIdTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val serviceItem1 = Service(carItem.carId, "description_a", 1000.0, -1)
        serviceDao.insert(serviceItem1)
        val serviceItem2 = Service(carItem.carId, "description_b", 2000.0, 0)
        serviceDao.insert(serviceItem2)
        serviceDao.deleteAllByCarId(carItem.carId)

        val allCars = serviceDao.getAllByCarId(carItem.carId).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(serviceItem1)
        Truth.assertThat(allCars).doesNotContain(serviceItem2)
    }

    @Test
    fun getSumOfServicesByCarIdTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val serviceItem1 = Service(carItem.carId, "description_a", 1000.0, -1)
        serviceDao.insert(serviceItem1)
        val serviceItem2 = Service(carItem.carId, "description_b", 2000.0, 0)
        serviceDao.insert(serviceItem2)
        val sum = serviceDao.getSumOfServicesByCarId(carItem.carId).getOrAwaitValue()

        val allCars = serviceDao.getAllByCarId(carItem.carId).getOrAwaitValue()
        Truth.assertThat(allCars.sumOf { service -> service.cost }).isEqualTo(sum)
    }
}