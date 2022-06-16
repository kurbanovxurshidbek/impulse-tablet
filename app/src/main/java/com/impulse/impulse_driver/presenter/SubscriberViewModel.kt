package com.impulse.impulse_driver.presenter


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.impulse.impulse_driver.database.Event
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine
import com.impulse.impulse_driver.model.PatientInfo


import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: MedicineRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Medicine
    private lateinit var baseSaveInfo : BaseMedicine
    private lateinit var patientInfo : PatientInfo

    @Bindable
    val drugsName = MutableLiveData<String?>()

    @Bindable
    val drugsAmount = MutableLiveData<Int?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    @Bindable
    val fullName = MutableLiveData<String?>()

    @Bindable
    val callStatus = MutableLiveData<String?>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Saqlash"
        clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
    }

    fun saveBaseDatabase() {
        if (fullName.value == null) {
            statusMessage.value = Event("Xato")
        }else {
            val fullName = fullName.value!!
            val callStatus = callStatus.value!!
            insertBase(BaseMedicine(0,fullName,callStatus,"",2,2,3,3))
        }
    }

    fun saveOrUpdate() {
        if (drugsName.value == null || drugsName.value == "") {
            statusMessage.value = Event("Iltimos dorining nomini kiriting")
        }else if (drugsAmount.value == null) {
            val name = drugsName.value!!
            val amount = 1
            insert(Medicine(0,name,amount))
            drugsName.value = null
        }
            else {
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
            statusMessage.value = Event("Omadli yakunlandi")
        }else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun insertBase(subscriber: BaseMedicine) : Job = viewModelScope.launch {
        val newRowId = repository.insertAll(subscriber)
        if (newRowId>-1) {
            statusMessage.value = Event("Omadli yakunlandi")
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
            saveOrUpdateButtonText.value = "Saqlash"
            clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
            statusMessage.value = Event("$noOfRows O`zgartirildi")
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
            saveOrUpdateButtonText.value = "Saqlash"
            clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
            statusMessage.value = Event("$noOfRowsDeleted Barchasi o'chirildi")
        }else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun clearAll() : Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted>0) {
            statusMessage.value = Event("$noOfRowsDeleted O`chirish omadli yakunlandi")
        }else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun initUpdateAndDelete(subscriber: Medicine) {
        drugsName.value = subscriber.name
        drugsAmount.value = subscriber.amount
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "O`zgartirish"
        clearAllOrDeleteButtonText.value = "O`chirish"

    }

    fun initUpdateAndDeleteBase(subscriber: BaseMedicine) {
        fullName.value = subscriber.fullName
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}