package com.impulse.impulse_driver.database

import com.impulse.impulse_driver.database.dao.MedicineDAO
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine


class MedicineRepository(private val dao : MedicineDAO) {

    val subscribers = dao.getAllSubscribers()
    val subscribers_base = dao.getAllBase()

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

    /** Base database **/

    suspend fun insertAll(baseInsert: BaseMedicine) : Long {
        return dao.insertSubscriberAll(baseInsert)
    }

    suspend fun updateAll(baseUpdate: BaseMedicine) : Int {
        return dao.updateSubscriberAll(baseUpdate)
    }

    suspend fun delete(baseDelete: BaseMedicine) : Int {
        return dao.deleteSubscriberAll(baseDelete)
    }

    suspend fun bDeleteAll() : Int {
        return dao.bDeleteAll()
    }
}