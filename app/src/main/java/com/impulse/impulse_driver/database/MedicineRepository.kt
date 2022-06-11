package com.impulse.impulse_driver.database

import com.impulse.impulse_driver.database.dao.MedicineDAO
import com.impulse.impulse_driver.database.entity.Medicine


class MedicineRepository(private val dao : MedicineDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Medicine) : Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Medicine) : Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Medicine) : Int {
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}