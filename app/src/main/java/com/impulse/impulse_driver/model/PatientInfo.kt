package com.impulse.impulse_driver.model

import android.print.PrintJobId
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class PatientInfo {
    var fullName: String? = null
    var addressName: String? = null
    var age: Int? = null
    var weight: Int? = null
    var height: Int? = null
    var callStatus: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var patientImg: String? = null
    var phoneNumber : String? = null
    var cardNumber : Int? = null
    var bloodGroup : Int? = null

    constructor(patientImg: String,fullName: String,addressName: String,callStatus: String,age: Int,weight: Int,height: Int,cardNumber : Int,bloodGroup : Int) {
        this.patientImg = patientImg
        this.bloodGroup = bloodGroup
        this.fullName = fullName
        this.addressName = addressName
        this.callStatus = callStatus
        this.age = age
        this.weight = weight
        this.height = height
        this.cardNumber = cardNumber
    }

    constructor(fullName: String,addressName: String,callStatus: String,phoneNumber: String) {
        this.fullName = fullName
        this.addressName = addressName
        this.callStatus = callStatus
        this.phoneNumber = phoneNumber
    }

    constructor(fullName: String,addressName: String,callStatus: String,age: Int) {
        this.fullName = fullName
        this.addressName = addressName
        this.callStatus = callStatus
        this.age = age
    }
}