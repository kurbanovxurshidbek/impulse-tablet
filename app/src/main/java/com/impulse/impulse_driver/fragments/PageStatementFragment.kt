package com.impulse.impulse_driver.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.PageStatementAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentStatementPageBinding
import com.impulse.impulse_driver.listener.QuantityListenerStatement
import com.impulse.impulse_driver.model.CheckboxStatement
import com.impulse.impulse_driver.model.NewBase
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.utils.ARG


class PageStatementFragment : BaseFragment(),QuantityListenerStatement {
    private var _binding: FragmentStatementPageBinding? = null
    private val binding get() = _binding!!
    private var fragment: PageStatementFragment? = null
    private lateinit var quantityAdapter: PageStatementAdapter
    private lateinit var patientInfo: PatientInfo

    fun newInstance(): PageStatementFragment? {
        return PageStatementFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentStatementPageBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {
        binding.apply {
            lottieAnimations.setAnimation("sos_doctor.json")

            saveAppInfoUser()
            saveInfoDefault()
            checkedWatcher()
        }
        setRecyclerView()
    }

    private fun saveInfoDefault() {
        binding.apply {
            ARG.TIME = NewBase(
                district = etDistrict.text.toString(),doctor = etDoctor.text.toString(), feldsher = etFeldsher.text.toString(),
                street = etStreet.text.toString(), home = etHome.text.toString(), apartment = etApartment.text.toString(),
                phoneNumber = etPhoneNumber.text.toString(), fullName = etFullName.text.toString(), age = etPatientAge.text.toString(),
                call_patient = etCallPatient.text.toString(), residence_address = etResidenceAddress.text.toString()
            )
        }
    }

    private fun checkedWatcher() {
        binding.apply {
            val mTextWatcher = object : TextWatcher {
                override fun afterTextChanged(et: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    ARG.TIME = NewBase(
                        institution_name = etInstitutionName.text.toString(), station_name = etStationName.text.toString(),
                        station_number = etStationNumber.text.toString(), district = etDistrict.text.toString(),
                        street = etStreet.text.toString(), home = etHome.text.toString(), apartment = etApartment.text.toString(),
                        phoneNumber = etPhoneNumber.text.toString(), fullName = etFullName.text.toString(), age = etPatientAge.text.toString(),
                        call_patient = etCallPatient.text.toString(), residence_address = etResidenceAddress.text.toString(),
                        doctor = etDoctor.text.toString(), feldsher = etFeldsher.text.toString(), sanitary = etSanitary.text.toString(),
                        dispatcher = etDispatcher.text.toString(), driver = etDriver.text.toString(), board_number = etBoardNumber.text.toString(),
                    )
                }
            }
            etInstitutionName.addTextChangedListener(mTextWatcher)
            etStationName.addTextChangedListener(mTextWatcher)
            etStationNumber.addTextChangedListener(mTextWatcher)
            etDistrict.addTextChangedListener(mTextWatcher)
            etStreet.addTextChangedListener(mTextWatcher)
            etHome.addTextChangedListener(mTextWatcher)
            etApartment.addTextChangedListener(mTextWatcher)
            etPhoneNumber.addTextChangedListener(mTextWatcher)
            etFullName.addTextChangedListener(mTextWatcher)
            etPatientAge.addTextChangedListener(mTextWatcher)
            etCallPatient.addTextChangedListener(mTextWatcher)
            etResidenceAddress.addTextChangedListener(mTextWatcher)
            etDoctor.addTextChangedListener(mTextWatcher)
            etFeldsher.addTextChangedListener(mTextWatcher)
            etSanitary.addTextChangedListener(mTextWatcher)
            etDispatcher.addTextChangedListener(mTextWatcher)
            etDriver.addTextChangedListener(mTextWatcher)
            etBoardNumber.addTextChangedListener(mTextWatcher)
        }
    }

    fun getQuantityData(): ArrayList<CheckboxStatement> {
        var arrayList : ArrayList<CheckboxStatement> = ArrayList()
        arrayList.add(CheckboxStatement(getString(R.string.str_reasons_call),0))
        arrayList.add(CheckboxStatement(getString(R.string.str_emergencies),getString(R.string.str_mental_disorder),getString(R.string.str_fall_heightr),1))
        arrayList.add(CheckboxStatement(getString(R.string.str_chest_pain),getString(R.string.str_worsening_breathing),getString(R.string.str_severe_pain),1))
        arrayList.add(CheckboxStatement(getString(R.string.str_blood_pressure),getString(R.string.str_high_temperature),getString(R.string.str_allergy),1))
        arrayList.add(CheckboxStatement(getString(R.string.str_epilepsy),getString(R.string.str_pregnancy),getString(R.string.str_bleeding),1))
        arrayList.add(CheckboxStatement(getString(R.string.str_mental_disorder_px),getString(R.string.str_activity),getString(R.string.str_death_condition),1))
        arrayList.add(CheckboxStatement(getString(R.string.str_call_brigade),0))
        arrayList.add(CheckboxStatement(getString(R.string.str_resuscitation),getString(R.string.str_simple),getString(R.string.str_feldsher),getString(R.string.str_call_nerve),2))
        return arrayList
    }

    private fun setRecyclerView() {
        binding.apply {
            rvStatement.setHasFixedSize(true)
            rvStatement.layoutManager = GridLayoutManager(requireContext(),1)
            quantityAdapter = PageStatementAdapter(requireContext(),getQuantityData(),this@PageStatementFragment)
            rvStatement.adapter = quantityAdapter
        }
    }

    override fun onQuantityChange(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_fragment = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeQuartet(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_fragment_two = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

   fun saveAppInfoUser() {
       patientInfo = PatientInfo()
       binding.apply {
           etDistrict.setText(patientInfo.districtName)
           etStreet.setText(patientInfo.street)
           etHome.setText(patientInfo.homeNumber)
           etApartment.setText(patientInfo.apartment)
           etPhoneNumber.setText(patientInfo.phoneNumber)
           etFullName.setText(patientInfo.fullName)
           etPatientAge.setText(patientInfo.age.toString())
           etResidenceAddress.setText(patientInfo.residence_address)
       }
   }
}