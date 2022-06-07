package com.impulse.impulse_driver.model

import android.print.PrintJobId
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MyLocation(
    var status: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)