package com.impulse.impulse_driver.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.fragments.MedicineViewModel
import java.lang.IllegalArgumentException


class PostViewModelFactory(private val dao: PostDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if  (modelClass.isAssignableFrom(MedicineViewModel::class.java)){
            return MedicineViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}