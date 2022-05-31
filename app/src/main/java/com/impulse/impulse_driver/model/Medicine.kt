package com.impulse.impulse_driver.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "posts")
data class Medicine(@PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var medicineName: String? = null,
    var amount: String? = null,
    var isLoaded: Boolean = true
):Serializable