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
    @ColumnInfo(name = "cardNumber")
    var cardNumber : String,
    @ColumnInfo(name = "cardNumberSecond")
    var cardNumberSecond : String,
    @ColumnInfo(name = "groupN")
    var groupN : String,
    @ColumnInfo(name = "currentDate")
    var currentDate : String,
    @ColumnInfo(name = "ch_patient")
    var ch_patient : String,
    @ColumnInfo(name = "ch_operator")
    var ch_operator : String,
    @ColumnInfo(name = "ch_brigade")
    var ch_brigade : String,
    @ColumnInfo(name = "currentTimes")
    var currentTimes : String,
    @ColumnInfo(name = "et_time")
    var et_time : String,
    @ColumnInfo(name = "et_timeP")
    var et_timeP : String,
    @ColumnInfo(name = "et_timeS")
    var et_timeS : String,
    @ColumnInfo(name = "et_timeFinish")
    var et_timeFinish : String,
    @ColumnInfo(name = "time_fragment")
    var time_fragment : String,
    @ColumnInfo(name = "time_fragment_two")
    var time_fragment_two : String,
    @ColumnInfo(name = "time_fragment_three")
    var time_fragment_three : String,
    @ColumnInfo(name = "chAmbulanse_name")
    var chAmbulanse_name : String,
    @ColumnInfo(name = "doctor_name")
    var doctor_name : String,
    @ColumnInfo(name = "signature")
    var signature : String,
    @ColumnInfo(name = "signaturePerson")
    var signaturePerson : String,
    @ColumnInfo(name = "indicators1")
    var indicators1 : String,
    @ColumnInfo(name = "indicators2")
    var indicators2 : String,
    @ColumnInfo(name = "indicators3")
    var indicators3 : String,
    @ColumnInfo(name = "indicators4")
    var indicators4 : String,
    @ColumnInfo(name = "indicators5")
    var indicators5 : String,
    @ColumnInfo(name = "indicators6")
    var indicators6 : String,
    @ColumnInfo(name = "indicators7")
    var indicators7 : String,
    @ColumnInfo(name = "indicators8")
    var indicators8 : String,
    @ColumnInfo(name = "indicators9")
    var indicators9 : String,
    @ColumnInfo(name = "indicators10")
    var indicators10 : String,
    @ColumnInfo(name = "indicators11")
    var indicators11 : String,
    @ColumnInfo(name = "indicators12")
    var indicators12 : String,
    @ColumnInfo(name = "indicators13")
    var indicators13 : String,
    @ColumnInfo(name = "indicators14")
    var indicators14 : String,
    @ColumnInfo(name = "indicators15")
    var indicators15 : String,
    @ColumnInfo(name = "indicators16")
    var indicators16 : String,
    @ColumnInfo(name = "indicators17")
    var indicators17 : String,
    @ColumnInfo(name = "indicators18")
    var indicators18 : String,
    @ColumnInfo(name = "fullName")
    var fullName : String,
    @ColumnInfo(name = "callStatus")
    var callStatus : String,
    @ColumnInfo(name = "addressName")
    var addressName : String,
    @ColumnInfo(name = "age")
    var age : Int,
    @ColumnInfo(name = "weight")
    var weight : Int,
    @ColumnInfo(name = "height")
    var height : Int,
    @ColumnInfo(name = "institution_name")
    var institution_name : String,
)
