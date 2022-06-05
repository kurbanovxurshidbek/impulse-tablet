package com.impulse.impulse_driver.presenter


import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.impulse.impulse_driver.database.Event
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.model.Medicine


import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: MedicineRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Medicine

    @Bindable
    val drugsName = MutableLiveData<String?>()

    @Bindable
    val drugsAmount = MutableLiveData<Int?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        if (drugsName.value == null) {
            statusMessage.value = Event("Please enter subscriber`s name")
        }else {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = drugsName.value!!
                subscriberToUpdateOrDelete.amount = drugsAmount.value!!
                update(subscriberToUpdateOrDelete)
            }else {
                val name = drugsName.value!!
                val email = drugsAmount.value!!
                insert(Medicine(0,name,email))
                drugsName.value = null
                drugsAmount.value = 1
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        }else {
            clearAll()
        }
    }

    fun insert(subscriber: Medicine) : Job = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
        if (newRowId>-1) {
            statusMessage.value = Event("Subscriber Inserted Successfully")
        }else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun update(subscriber: Medicine) : Job = viewModelScope.launch {
        val noOfRows = repository.update(subscriber)
        if (noOfRows>0) {
            drugsName.value = null
            drugsAmount.value = 1
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        }else {
            statusMessage.value = Event("Error occurred")
        }

    }

    fun delete(subscriber: Medicine) : Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)

        if (noOfRowsDeleted>0) {
            drugsName.value = null
            drugsAmount.value = 1
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        }else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun clearAll() : Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted>0) {
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        }else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun initUpdateAndDelete(subscriber: Medicine) {
        drugsName.value = subscriber.name
        drugsAmount.value = subscriber.amount
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}