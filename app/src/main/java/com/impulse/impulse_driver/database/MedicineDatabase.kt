package com.impulse.impulse_driver.database


import android.content.Context
import androidx.room.*
import com.impulse.impulse_driver.database.dao.MedicineDAO
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine
import com.impulse.impulse_driver.utils.HobbiesTypeConverter

@Database(entities = [Medicine::class,BaseMedicine::class], version = 1)
@TypeConverters(HobbiesTypeConverter::class)
abstract class MedicineDatabase : RoomDatabase() {
    abstract val subscriberDao: MedicineDAO

    companion object {
        @Volatile
        private var INSTANCE: MedicineDatabase? = null

        fun getInstance(context: Context): MedicineDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicineDatabase::class.java,
                        "medicine_dao"
                    ).build()
                }
                return instance
            }
        }

    }
}