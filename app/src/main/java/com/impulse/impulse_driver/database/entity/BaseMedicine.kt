package com.impulse.impulse_driver.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "base_data_table")
data class BaseMedicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")
    var id : Int,
    @ColumnInfo(name = "fullName")
    var fullName : String,
    @ColumnInfo(name = "callStatus")
    var callStatus : String,
    @ColumnInfo(name = "addressName")
    var addressName : String,
    @ColumnInfo(name = "cardNumber")
    var cardNumber : Int,
    @ColumnInfo(name = "age")
    var age : Int,
    @ColumnInfo(name = "weight")
    var weight : Int,
    @ColumnInfo(name = "height")
    var height : Int,
)
