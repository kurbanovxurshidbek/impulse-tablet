package com.impulse.impulse_driver.model

import android.print.PrintJobId
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class PatientInfo {
    var fullName: String? = "Asil Absukarimov"
    var age: Int? = 23
    var weight: Int? = 72
    var height: Int? = 175
    var callStatus: String? = "ogir"
    var latitude: Double? = null
    var longitude: Double? = null
    var patientImg: String? = "https://images.unsplash.com/photo-1658158672450-6fc5fd6957db?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
    var phoneNumber : String? = "+9983323232"
    var cardNumber : Int? = 11020
    var bloodGroup : Int? = 3
    var came_ambulance : String? = null
    var districtName : String? = "Tashkent"
    var street : String? = "Pushkin ko'chasi"
    var homeNumber : String? = "24B"
    var apartment : String? = "12"
    var callPatient : String? = "Ubaydullayev Ilhombek"
    var residence_address : String? = "Shayxantohur Tumani"

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