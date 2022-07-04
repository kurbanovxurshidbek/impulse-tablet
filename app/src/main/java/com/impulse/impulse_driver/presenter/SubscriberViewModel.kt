package com.impulse.impulse_driver.presenter


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.impulse.impulse_driver.model.NewBase
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.utils.ARG
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SubscriberViewModel(private val repository: MedicineRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    val subscribers_base = repository.subscribers_base
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Medicine
    private lateinit var baseSaveInfo: Medicine
    private lateinit var patientInfo: PatientInfo


    @Bindable
    val drugsName = MutableLiveData<String?>()

    @Bindable
    val drugsAmount = MutableLiveData<Int>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    @Bindable
    val callStatus = MutableLiveData<String>()

    @Bindable
    val institution_name = MutableLiveData<String>()

    @Bindable
    val time_fragment = MutableLiveData<String>()

    @Bindable
    val time_fragment_two = MutableLiveData<String>()

    @Bindable
    val time_fragment_three = MutableLiveData<String>()

    @Bindable
    val add_infoSecond = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()


    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Saqlash"
        clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
    }

    fun saveDatabaseFragment(baseMedicine: ARG) {
        patientInfo = PatientInfo()
        baseSaveInfo = Medicine()
        Log.d("institution_name", institution_name.value.toString())
        insertBase(BaseMedicine(cardNumber = baseMedicine.TIMES.cardNumber, cardNumberSecond = baseMedicine.TIMES.cardNumberSecond,
            groupN = baseMedicine.TIMES.groupN, currentDate = baseMedicine.TIMES.currentDate, ch_patient = baseMedicine.chPatient,
            ch_brigade = baseMedicine.et_time, ch_operator = baseMedicine.chOperator, currentTimes = baseMedicine.TIMES.currentTime,
            et_time = baseMedicine.TIMES.et_time, et_timeP = baseMedicine.TIMES.et_timeP, et_timeS = baseMedicine.TIMES.et_timeS,
            et_timeFinish = baseMedicine.TIMES.et_timeFinish, time_fragment = baseMedicine.time_fragment, time_fragment_two = baseMedicine.time_fragment_two,
            time_fragment_three = baseMedicine.time_fragment_three, chAmbulanse_name = baseMedicine.TIMES.chAmbulanse_name,
            doctor_name = baseMedicine.TIMES.doctor_name, signature = baseMedicine.TIMES.signature, signaturePerson = baseMedicine.TIMES.signaturePerson,
            indicators1 = baseMedicine.TIMES.indicators1,indicators2 = baseMedicine.TIMES.indicators2,indicators3 = baseMedicine.TIMES.indicators3,
            indicators4 = baseMedicine.TIMES.indicators4,indicators5 = baseMedicine.TIMES.indicators5,indicators6 = baseMedicine.TIMES.indicators6,
            indicators7 = baseMedicine.TIMES.indicators7,indicators8 = baseMedicine.TIMES.indicators8,indicators9 = baseMedicine.TIMES.indicators9,
            indicators10 = baseMedicine.TIMES.indicators10,indicators11 = baseMedicine.TIMES.indicators11,indicators12 = baseMedicine.TIMES.indicators12,
            indicators13 = baseMedicine.TIMES.indicators13,indicators14 = baseMedicine.TIMES.indicators14,indicators15 = baseMedicine.TIMES.indicators15,
            indicators16 = baseMedicine.TIMES.indicators16,indicators17 = baseMedicine.TIMES.indicators17,indicators18 = baseMedicine.TIMES.indicators18,
            institution_name = baseMedicine.TIME.institution_name, station_name = baseMedicine.TIME.station_name, station_number = baseMedicine.TIME.station_number,
            street = baseMedicine.TIME.street, home = baseMedicine.TIME.home, apartment = baseMedicine.TIME.apartment, phoneNumber = baseMedicine.TIME.phoneNumber,
            age = baseMedicine.TIME.age, call_patient = baseMedicine.TIME.call_patient, doctor = baseMedicine.TIME.doctor, feldsher = baseMedicine.TIME.feldsher,
            sanitary = baseMedicine.TIME.sanitary, dispatcher = baseMedicine.TIME.dispatcher, driver = baseMedicine.TIME.driver, board_number = baseMedicine.TIME.board_number,
            statement_fragment = baseMedicine.statement_fragment, statement_fragment_two = baseMedicine.statement_fragment_two, callStatus = patientInfo.callStatus,
            fullName = baseMedicine.TIME.fullName,weight = patientInfo.weight, height = patientInfo.height, residence_address = baseMedicine.TIME.residence_address,
            addressName = baseMedicine.TIME.district, statement_continue_one = baseMedicine.statement_continue_one, statement_continue_two = baseMedicine.statement_continue_two,
            statement_continue_three = baseMedicine.statement_continue_three, statement_continue_four = baseMedicine.statement_continue_four, statement_continue_five = baseMedicine.statement_continue_five,
            statement_continue_six = baseMedicine.statement_continue_six, statement_continue_seven = baseMedicine.statement_continue_seven, statement_continue_eight = baseMedicine.statement_continue_eight,
            statement_continue_nine = baseMedicine.statement_continue_nine, statement_continue_teen = baseMedicine.statement_continue_teen, add_info = baseMedicine.MEDICINE.add_info,
            add_infoSecond = baseMedicine.MEDICINE.add_infoSecond, drugs = baseMedicine.drugs, patientImg = patientInfo.patientImg, bloodGroup = patientInfo.bloodGroup.toString()))
    }
    fun updateBase(baseMedicine: BaseMedicine): Job = viewModelScope.launch {
        val noOfRows = repository.updateAll(baseMedicine)
        if (noOfRows > 0) {

        } else {
            statusMessage.value = Event("Error occurred")
        }

    }

    fun saveOrUpdate() {
        if (drugsName.value == null || drugsName.value == "") {
            statusMessage.value = Event("Iltimos dorining nomini kiriting")
        } else if (drugsAmount.value == null) {
            val name = drugsName.value!!
            val amount = 1
            insert(Medicine(name = name, amount = amount))
            ARG.drugs?.add("$name , $amount")
            drugsName.value = null
        } else {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = drugsName.value!!
                subscriberToUpdateOrDelete.amount = drugsAmount.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                val name = drugsName.value!!
                val amount = drugsAmount.value!!
                insert(Medicine(name = name, amount = amount))
                ARG.drugs?.add("$name , $amount")
                drugsName.value = null
                drugsAmount.value = 1
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun insert(subscriber: Medicine): Job = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Omadli yakunlandi")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun insertBase(subscriber: BaseMedicine): Job = viewModelScope.launch {
        val newRowId = repository.insertAll(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Omadli yakunlandi")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(subscriber: Medicine): Job = viewModelScope.launch {
        val noOfRows = repository.update(subscriber)
        if (noOfRows > 0) {
            drugsName.value = null
            drugsAmount.value = 1
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Saqlash"
            clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
            statusMessage.value = Event("$noOfRows O`zgartirildi")
        } else {
            statusMessage.value = Event("Error occurred")
        }

    }

    fun delete(subscriber: Medicine): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)
        if (noOfRowsDeleted > 0) {
            drugsName.value = null
            drugsAmount.value = 1
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Saqlash"
            clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
            statusMessage.value = Event("$noOfRowsDeleted Barchasi o'chirildi")
        } else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted O`chirish omadli yakunlandi")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun initUpdateAndDelete(subscriber: Medicine) {
        drugsName.value = subscriber.name
        drugsAmount.value = subscriber.amount!!
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "O`zgartirish"
        clearAllOrDeleteButtonText.value = "O`chirish"
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}