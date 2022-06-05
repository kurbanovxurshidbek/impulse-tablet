package com.impulse.impulse_driver.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "subscriber_data_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "drugs_id")
    var id : Int,
    @ColumnInfo(name = "drugs_name")
    var name : String,
    @ColumnInfo(name = "drugs_amount")
    var amount : Int
)