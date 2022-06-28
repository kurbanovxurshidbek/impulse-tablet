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
import com.impulse.impulse_driver.model.PatientInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SubscriberViewModel(private val repository: MedicineRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Medicine
    private lateinit var baseSaveInfo : BaseMedicine
    private lateinit var patientInfo : PatientInfo


    @Bindable
    val drugsName = MutableLiveData<String?>()

    @Bindable
    val drugsAmount = MutableLiveData<Int>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    @Bindable
    val fullName = MutableLiveData<String>()

    @Bindable
    val patientAge = MutableLiveData<String>()

    @Bindable
    val callPatient = MutableLiveData<String>()

    @Bindable
    val residence_address = MutableLiveData<String>()

    @Bindable
    val callStatus = MutableLiveData<String>()

    @Bindable
    val cardNumber = MutableLiveData<String>()

    @Bindable
    val cardNumberSecond = MutableLiveData<String?>()

    @Bindable
    val groupN = MutableLiveData<String?>()

    @Bindable
    val currentDate = MutableLiveData<String?>()

    @Bindable
    val currentTimes = MutableLiveData<String>()

    @Bindable
    val ch_patient = MutableLiveData<String>()

    @Bindable
    val ch_operator = MutableLiveData<String>()

    @Bindable
    val ch_brigade = MutableLiveData<String>()

    @Bindable
    val et_time = MutableLiveData<String>()

    @Bindable
    val et_timeP = MutableLiveData<String>()

    @Bindable
    val et_timeS = MutableLiveData<String>()

    @Bindable
    val et_timeFinish = MutableLiveData<String>()

    @Bindable
    val chAmbulanse_name = MutableLiveData<String>()

    @Bindable
    val doctor_name = MutableLiveData<String>()

    @Bindable
    val signature = MutableLiveData<String>()

    @Bindable
    val signaturePerson = MutableLiveData<String>()

    @Bindable
    val indicators1 = MutableLiveData<String>()

    @Bindable
    val indicators2 = MutableLiveData<String>()

    @Bindable
    val indicators3 = MutableLiveData<String>()

    @Bindable
    val indicators4 = MutableLiveData<String>()

    @Bindable
    val indicators5 = MutableLiveData<String>()

    @Bindable
    val indicators6 = MutableLiveData<String>()

    @Bindable
    val indicators7 = MutableLiveData<String>()

    @Bindable
    val indicators8 = MutableLiveData<String>()

    @Bindable
    val indicators9 = MutableLiveData<String>()

    @Bindable
    val indicators10 = MutableLiveData<String>()

    @Bindable
    val indicators11 = MutableLiveData<String>()

    @Bindable
    val indicators12 = MutableLiveData<String>()

    @Bindable
    val indicators13 = MutableLiveData<String>()

    @Bindable
    val indicators14 = MutableLiveData<String>()

    @Bindable
    val indicators15 = MutableLiveData<String>()

    @Bindable
    val indicators16 = MutableLiveData<String>()

    @Bindable
    val indicators17 = MutableLiveData<String>()

    @Bindable
    val indicators18 = MutableLiveData<String>()

    @Bindable
    val institution_name = MutableLiveData<String>()

    @Bindable
    val time_fragment = MutableLiveData<String>()

    @Bindable
    val time_fragment_two = MutableLiveData<String>()

    @Bindable
    val time_fragment_three = MutableLiveData<String>()

    @Bindable
    val station = MutableLiveData<String>()

    @Bindable
    val district = MutableLiveData<String>()

    @Bindable
    val street = MutableLiveData<String>()

    @Bindable
    val home = MutableLiveData<String>()

    @Bindable
    val apartment = MutableLiveData<String>()

    @Bindable
    val doctor = MutableLiveData<String>()

    @Bindable
    val feldsher = MutableLiveData<String>()

    @Bindable
    val sanitary = MutableLiveData<String>()

    @Bindable
    val dispatcher = MutableLiveData<String>()

    @Bindable
    val driver = MutableLiveData<String>()

    @Bindable
    val board_number = MutableLiveData<String>()

    @Bindable
    val distance = MutableLiveData<String>()

    @Bindable
    val number_ps = MutableLiveData<String>()

    @Bindable
    val add_info = MutableLiveData<String>()

    @Bindable
    val add_infoSecond = MutableLiveData<String>()

    @Bindable
    val phoneNumber = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()


    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Saqlash"
        clearAllOrDeleteButtonText.value = "Barchasini o`chirish"
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTimeFragment() {
         patientInfo = PatientInfo()
        cardNumber.value = patientInfo.cardNumber.toString()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        currentDate.value = formatted
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formattedTime = current.format(formatterTime)
        currentTimes.value = formattedTime
        Log.d("came",ch_patient.value.toString())
    }

    fun saveDatabaseFragment() {
        patientInfo = PatientInfo()
        statementFragment()
        Log.d("institution_name",institution_name.value.toString())
        insertBase(BaseMedicine(0,cardNumber.value!!,cardNumberSecond.value!!,groupN.value!!,currentDate.value!!,ch_patient.value!!,
            ch_operator.value!!,ch_brigade.value!!,currentTimes.value!!,et_time.value!!,et_timeP.value!!,
        et_timeS.value!!,et_timeFinish.value!!,time_fragment.value.toString(),time_fragment_two.value.toString(),time_fragment_three.value.toString(),
        chAmbulanse_name.value.toString(),doctor_name.value.toString(),signature.value.toString(),signaturePerson.value.toString(),indicators1.value.toString(),indicators2.value.toString(),
            indicators3.value.toString(),indicators4.value.toString(),indicators5.value.toString(),indicators6.value.toString(),indicators7.value.toString(),indicators8.value.toString(),
            indicators9.value.toString(),indicators10.value.toString(),indicators11.value.toString(),indicators12.value.toString(),indicators13.value.toString(),
            indicators14.value.toString(),indicators15.value.toString(),indicators16.value.toString(),indicators17.value.toString(),indicators18.value.toString(),
        fullName.value.toString(),callStatus.value.toString(),street.value.toString(),patientAge.value!!.toInt(),patientInfo.weight!!,patientInfo.height!!,institution_name.value.toString()))
    }

    fun saveBaseDatabase() {
        if (fullName.value == null) {
            statusMessage.value = Event("Xato")
        }else {
            val fullName = fullName.value!!
            val callStatus = callStatus.value!!
//            insertBase(BaseMedicine(0,fullName,callStatus,"",2,2,3,3))
        }
    }

    fun stamentTwo() {
        patientInfo = PatientInfo()
        patientAge.value = patientInfo.age.toString()
        var institution_names = institution_name.value.toString()
        Log.d("ffff",institution_names)
    }

    fun statementFragment() {
        patientInfo = PatientInfo()
        callStatus.value = patientInfo.callStatus
        district.value = patientInfo.districtName
        street.value = patientInfo.street
        home.value = patientInfo.homeNumber
        apartment.value = patientInfo.apartment
        phoneNumber.value = patientInfo.phoneNumber
        fullName.value = patientInfo.fullName
        patientAge.value = patientInfo.age.toString()
        callPatient.value = patientInfo.callPatient
        residence_address.value = patientInfo.residence_address
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