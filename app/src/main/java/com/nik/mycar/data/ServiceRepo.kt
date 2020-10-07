package com.nik.mycar.data

import androidx.lifecycle.LiveData

class ServiceRepo (private val serviceDao: ServiceDao) {

    val allServices: LiveData<List<Service>> = serviceDao.getAllService()

    suspend fun getService(key: Long) {
        serviceDao.getService(key)
    }

    suspend fun insert(service: Service) {
        serviceDao.insert(service)
    }

    suspend fun delete(key: Long) {
        serviceDao.delete(key)
    }

    suspend fun clear() {
        serviceDao.clear()
    }
}
