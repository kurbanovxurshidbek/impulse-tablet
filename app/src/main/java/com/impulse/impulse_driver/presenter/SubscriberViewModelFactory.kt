package com.impulse.impulse_driver.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.impulse.impulse_driver.database.MedicineRepository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val repository: MedicineRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model class")
    }
}