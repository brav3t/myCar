package com.nik.mycar.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
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
class CarDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var carDao: CarDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        carDao = database.carDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCarItemTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)

        val allCars = carDao.getAll().getOrAwaitValue()
        assertThat(allCars).contains(carItem)
    }

    @Test
    fun deleteCarItemTest() = runBlockingTest {
        val carItem = Car("car")
        carDao.insert(carItem)
        carDao.delete(carItem.carId)

        val allCars = carDao.getAll().getOrAwaitValue()
        assertThat(allCars).doesNotContain(carItem)
    }
}
