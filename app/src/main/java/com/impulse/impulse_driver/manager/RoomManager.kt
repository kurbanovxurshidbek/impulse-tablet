package com.impulse.impulse_driver.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.model.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class RoomManager: RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object{
        private var instance: RoomManager? = null

        fun getInstance(context: Context): RoomManager{
            if (instance == null){
                instance = Room.databaseBuilder(context, RoomManager::class.java, "database.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}