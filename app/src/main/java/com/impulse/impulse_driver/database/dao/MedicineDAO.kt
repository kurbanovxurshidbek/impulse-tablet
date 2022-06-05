package com.impulse.impulse_driver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.impulse.impulse_driver.model.Medicine

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

}