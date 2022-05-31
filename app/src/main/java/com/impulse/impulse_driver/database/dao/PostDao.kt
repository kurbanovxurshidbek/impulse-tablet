package com.impulse.impulse_driver.database.dao

import androidx.room.*
import com.impulse.impulse_driver.model.Medicine

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCard(card: Medicine)

    @Query("SELECT * FROM posts")
    fun getCards(): List<Medicine>

    @Query("SELECT * FROM posts where isLoaded=0")
    fun getOfflineCards(): List<Medicine>

    @Update
    fun update(card: Medicine)


}