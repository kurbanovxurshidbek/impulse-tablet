package com.impulse.impulse_driver.model

import android.print.PrintJobId
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class PatientInfo {
    var fullName: String? = "Ilhombek Ubaydullayev"
    var age: Int? = 25
    var weight: Int? = 62
    var height: Int? = 178
    var callStatus: String? = "yengil"
    var latitude: Double? = null
    var longitude: Double? = null
    var patientImg: String? = "https://images.unsplash.com/photo-1655207162062-766b99476e6a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80"
    var phoneNumber : String? = "+998995243536"
    var cardNumber : Int? = 32342
    var bloodGroup : Int? = 3
    var came_ambulance : String? = null
    var districtName : String? = "Uzbekistan"
    var street : String? = "Nurafshon ko'chasi"
    var homeNumber : String? = "35B"
    var apartment : String? = "34"
    var callPatient : String? = "Ubaydullayev Ilhombek"
    var residence_address : String? = "Tashkent Tumani"

    constructor(patientImg: String,fullName: String,addressName: String,callStatus: String,age: Int,weight: Int,height: Int,cardNumber : Int,bloodGroup : Int) {
        this.patientImg = patientImg
        this.bloodGroup = bloodGroup
        this.fullName = fullName
        this.street = addressName
        this.callStatus = callStatus
        this.age = age
        this.weight = weight
        this.height = height
        this.cardNumber = cardNumber
    }

    constructor(fullName: String,addressName: String,callStatus: String,phoneNumber: String) {
        this.fullName = fullName
        this.street = addressName
        this.callStatus = callStatus
        this.phoneNumber = phoneNumber
    }

    constructor(fullName: String,addressName: String,callStatus: String,age: Int) {
        this.fullName = fullName
        this.street = addressName
        this.callStatus = callStatus
        this.age = age
    }

    constructor() {

    }
}