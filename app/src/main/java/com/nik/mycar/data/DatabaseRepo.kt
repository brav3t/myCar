package com.nik.mycar.data

import androidx.lifecycle.LiveData

class DatabaseRepo(private val databaseDao: DatabaseDao) {

    val getAllData: LiveData<List<CarWithRelations>> = databaseDao.getAllData()
}
