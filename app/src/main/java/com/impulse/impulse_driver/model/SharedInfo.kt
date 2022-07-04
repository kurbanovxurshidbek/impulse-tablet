package com.impulse.impulse_driver.model

data class SharedInfo(
    var callStatus : String? = null,
    var cardNumber : String? = null,
    var currentDate : String? = null,
    var ch_patient : String? = null,
    var ch_operator : String? = null,
    var ch_brigade : String? = null,
    var addressName : String? = null,
    var street : String? = null,
    var phoneNumber : String? = null,
)