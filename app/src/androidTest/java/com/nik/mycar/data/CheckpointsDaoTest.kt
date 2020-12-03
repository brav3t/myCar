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
class CheckpointsDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var checkpointDao: CheckpointDao
    private lateinit var carDao: CarDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        checkpointDao = database.checkpointDao()
        carDao = database.carDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCheckpointItemTest() = runBlockingTest {
        val car = Car("car")
        carDao.insert(car)
        val checkpointItem = Checkpoint(car.carId, 12345, -1)
        checkpointDao.insert(checkpointItem)

        val allCars = checkpointDao.getAllByCarId(car.carId).getOrAwaitValue()
        Truth.assertThat(allCars).contains(checkpointItem)
    }

    @Test
    fun deleteAllCheckpointByCarIdTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val checkpointItem1 = Checkpoint(carItem.carId, 12345, -1)
        val checkpointItem2 = Checkpoint(carItem.carId, 12345, 0)
        checkpointDao.insert(checkpointItem1)
        checkpointDao.insert(checkpointItem2)
        checkpointDao.deleteAllByCarId(carItem.carId)

        val allCars = checkpointDao.getAllByCarId(carItem.carId).getOrAwaitValue()
        Truth.assertThat(allCars).doesNotContain(checkpointItem1)
        Truth.assertThat(allCars).doesNotContain(checkpointItem2)
    }

    @Test
    fun getLastCheckpointItemValueTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        val checkpointItem1 = Checkpoint(carItem.carId, 12345, -1)
        val checkpointItem2 = Checkpoint(carItem.carId, 12345, 0)
        checkpointDao.insert(checkpointItem1)
        checkpointDao.insert(checkpointItem2)
        val lastItem = checkpointDao.getLast(carItem.carId).getOrAwaitValue()

        val allCars = checkpointDao.getAllByCarId(carItem.carId).getOrAwaitValue()
        Truth.assertThat(allCars.last().checkpoint).isEqualTo(lastItem)
    }
}