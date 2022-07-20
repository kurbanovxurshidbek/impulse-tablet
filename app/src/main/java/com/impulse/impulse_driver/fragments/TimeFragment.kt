package com.impulse.impulse_driver.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentTimeBinding
import com.impulse.impulse_driver.listener.QuantityListener
import com.impulse.impulse_driver.model.CheckboxTime
import com.impulse.impulse_driver.model.NewBase
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory
import com.impulse.impulse_driver.utils.ARG
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList

/**
 * TimeFragment to accurately store information and time
 * **/
class TimeFragment : BaseFragment(),QuantityListener {
    private var _binding: FragmentTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var patientInfo: PatientInfo
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var quantityAdapter: QuantityAdapter
    private var fragment: TimeFragment? = null

    fun newInstance(): TimeFragment? {
        return TimeFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTimeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {
        val dao = MedicineDatabase.getInstance(requireActivity().application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.apply {
            myViewModel = subscriberViewModel
            lifecycleOwner = requireActivity()
            onCheckboxClicked()
            setRecyclerView()
            saveInfoDefault()
            checkedWatcher()
            btnSave.setOnClickListener {

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkedWatcher() {
        binding.apply {
            val mTextWatcher = object : TextWatcher {
                override fun afterTextChanged(et: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    ARG.TIMES = NewBase(cardNumber = etCardNumber.text.toString(),
                    cardNumberSecond = etTtyoCardNumber.text.toString(),
                    groupN = groupNumber.text.toString(),
                    currentDate = etData.text.toString(),
                    currentTime = etCurrentTimes.text.toString(),
                    et_time = etTime.text.toString(),
                    et_timeP = etTimeP.text.toString(),
                    et_timeS = etTimeS.text.toString(),
                    et_timeFinish = etTimeFinish.text.toString(),
                    chAmbulanse_name = etChAmbulanseName.text.toString(),
                    doctor_name = etDoctorName.text.toString(),
                    signature = etSignature.text.toString(),
                    signaturePerson = etSignaturePerson.text.toString(),
                    indicators1 = etIndicators1.text.toString(),
                    indicators2 = etIndicators2.text.toString(),
                    indicators3 = etIndicators3.text.toString(),
                    indicators4 = etIndicators4.text.toString(),
                    indicators5 = etIndicators5.text.toString(),
                    indicators6 = etIndicators6.text.toString(),
                    indicators7 = etIndicators7.text.toString(),
                    indicators8 = etIndicators8.text.toString(),
                    indicators9 = etIndicators9.text.toString(),
                    indicators10 = etIndicators10.text.toString(),
                    indicators11 = etIndicators11.text.toString(),
                    indicators12 = etIndicators12.text.toString(),
                    indicators13 = etIndicators13.text.toString(),
                    indicators14 = etIndicators14.text.toString(),
                    indicators15 = etIndicators15.text.toString(),
                    indicators16 = etIndicators16.text.toString(),
                    indicators17 = etIndicators17.text.toString(),
                    indicators18 = etIndicators18.text.toString())
                }
            }
            etCardNumber.addTextChangedListener(mTextWatcher)
            etTtyoCardNumber.addTextChangedListener(mTextWatcher)
            groupNumber.addTextChangedListener(mTextWatcher)
            etData.addTextChangedListener(mTextWatcher)
            etCurrentTimes.addTextChangedListener(mTextWatcher)
            etTime.addTextChangedListener(mTextWatcher)
            etTimeP.addTextChangedListener(mTextWatcher)
            etTimeS.addTextChangedListener(mTextWatcher)
            etTimeFinish.addTextChangedListener(mTextWatcher)
            etChAmbulanseName.addTextChangedListener(mTextWatcher)
            etDoctorName.addTextChangedListener(mTextWatcher)
            etSignature.addTextChangedListener(mTextWatcher)
            etSignaturePerson.addTextChangedListener(mTextWatcher)
            etIndicators1.addTextChangedListener(mTextWatcher)
            etIndicators2.addTextChangedListener(mTextWatcher)
            etIndicators3.addTextChangedListener(mTextWatcher)
            etIndicators4.addTextChangedListener(mTextWatcher)
            etIndicators5.addTextChangedListener(mTextWatcher)
            etIndicators6.addTextChangedListener(mTextWatcher)
            etIndicators7.addTextChangedListener(mTextWatcher)
            etIndicators8.addTextChangedListener(mTextWatcher)
            etIndicators9.addTextChangedListener(mTextWatcher)
            etIndicators10.addTextChangedListener(mTextWatcher)
            etIndicators11.addTextChangedListener(mTextWatcher)
            etIndicators12.addTextChangedListener(mTextWatcher)
            etIndicators13.addTextChangedListener(mTextWatcher)
            etIndicators14.addTextChangedListener(mTextWatcher)
            etIndicators15.addTextChangedListener(mTextWatcher)
            etIndicators16.addTextChangedListener(mTextWatcher)
            etIndicators17.addTextChangedListener(mTextWatcher)
            etIndicators18.addTextChangedListener(mTextWatcher)
        }
        saveTimeFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveTimeFragment() {
        binding.apply {
            patientInfo = PatientInfo()
            etCardNumber.setText(patientInfo.cardNumber.toString())
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = current.format(formatter)
            etData.setText(formatted)

            val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
            val formattedTime = current.format(formatterTime)
            etCurrentTimes.setText(formattedTime)
        }
    }

    fun getQuantityData():ArrayList<CheckboxTime>{
         var arrayList : ArrayList<CheckboxTime> = ArrayList()
         arrayList.add(CheckboxTime(getString(R.string.str_first_aid),0))
         arrayList.add(CheckboxTime(getString(R.string.str_not_specified),getString(R.string.str_specified_unsatisfactory),getString(R.string.str_specified_satisfactory),1))
         arrayList.add(CheckboxTime(getString(R.string.str_death_toreturn),0))
         arrayList.add(CheckboxTime(getString(R.string.str_came_aid),getString(R.string.str_ttyo_participation),getString(R.string.str_transportirovka),2))
         arrayList.add(CheckboxTime(getString(R.string.str_participation_with),0))
         arrayList.add(CheckboxTime(getString(R.string.str_disproportionate_injury),getString(R.string.str_acuse_disease),getString(R.string.str_phase_decompensasi),3))
         return arrayList
    }

    fun saveInfoDefault() {
        binding.apply {
            ARG.TIMES = NewBase(cardNumber = etCardNumber.text.toString(),
                currentDate = etData.text.toString(),
                currentTime = etCurrentTimes.text.toString(),
                et_time = etTime.text.toString())
        }
    }

    private fun setRecyclerView() {
        binding.apply {
            rvCheckbox.setHasFixedSize(true)
            rvCheckbox.layoutManager = GridLayoutManager(requireContext(),1)
            quantityAdapter = QuantityAdapter(requireContext(),getQuantityData(),this@TimeFragment)
            rvCheckbox.adapter = quantityAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onCheckboxClicked() {
        binding.apply {
            chPatient.setOnClickListener {
                if (chPatient.isChecked) {
                    chPatient2.isChecked = false
                    chPatient3.isChecked = false
                    chPatient4.isChecked = false
                    ARG.chPatient = chPatient.text.toString()
                }
            }

            chPatient2.setOnClickListener {
                if (chPatient2.isChecked) {
                    ARG.chPatient = chPatient2.text.toString()
                    chPatient.isChecked = false
                    chPatient3.isChecked = false
                    chPatient4.isChecked = false
                }
            }

            chPatient3.setOnClickListener {
                if (chPatient3.isChecked) {
                    ARG.chPatient = chPatient3.text.toString()
                    chPatient2.isChecked = false
                    chPatient.isChecked = false
                    chPatient4.isChecked = false
                }
            }

            chPatient4.setOnClickListener {
                if (chPatient4.isChecked) {
                    ARG.chPatient = chPatient4.text.toString()
                    chPatient2.isChecked = false
                    chPatient3.isChecked = false
                    chPatient.isChecked = false
                }
            }


            chOperator.setOnClickListener {
                if (chOperator.isChecked) {
                    chOperator2.setChecked(false)
                    ARG.chOperator = chOperator.text.toString()
                }
            }

            chOperator2.setOnClickListener {
                if (chOperator2.isChecked) {
                    chOperator.setChecked(false)
                    ARG.chOperator = chOperator2.text.toString()
                }
            }

            chBrigade.setOnClickListener {
                if (chBrigade.isChecked) {
                    chBrigade2.isChecked = false
                    ARG.et_time = chBrigade.text.toString()
                }
            }

            chBrigade2.setOnClickListener {
                if (chBrigade2.isChecked) {
                    chBrigade.isChecked = false
                    ARG.et_time = chBrigade2.text.toString()
                }
            }
        }
    }

    override fun onQuantityChange(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            ARG.time_fragment = i.toString()
            subscriberViewModel.time_fragment.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeTwo(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            ARG.time_fragment_two = i.toString()
            subscriberViewModel.time_fragment_two.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeThree(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            ARG.time_fragment_three = i.toString()
            subscriberViewModel.time_fragment_three.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.viewModelStore?.clear()
    }
}