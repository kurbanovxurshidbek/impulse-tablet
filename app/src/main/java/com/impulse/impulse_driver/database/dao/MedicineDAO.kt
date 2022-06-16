package com.impulse.impulse_driver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine

@Dao
interface MedicineDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Medicine): Long

    @Update
    suspend fun updateSubscriber(subscriber: Medicine) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Medicine) : Int

    @Query("DELETE FROM subscriber_data_table" )
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Medicine>>

    /** Base database **/

    @Insert
    suspend fun insertSubscriberAll(baseInsert: BaseMedicine): Long

    @Update
    suspend fun updateSubscriberAll(baseUpdate: BaseMedicine) : Int

    @Delete
    suspend fun deleteSubscriberAll(baseDelete: BaseMedicine) : Int

    @Query("DELETE FROM base_data_table" )
    suspend fun bDeleteAll() : Int

    @Query("SELECT * FROM base_data_table")
    fun getAllBase():LiveData<List<BaseMedicine>>
}