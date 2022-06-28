package com.impulse.impulse_driver.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentTimeBinding
import com.impulse.impulse_driver.listener.QuantityListener
import com.impulse.impulse_driver.model.CheckboxTime
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory
import java.util.ArrayList


class TimeFragment : BaseFragment(),QuantityListener {
    private var _binding: FragmentTimeBinding? = null
    private val binding get() = _binding!!
    private var patientInfo: PatientInfo? = null
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

            btnSave.setOnClickListener {
                subscriberViewModel.saveDatabaseFragment()
            }
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
                    subscriberViewModel.ch_patient.value = chPatient.text.toString()
                    chPatient2.isChecked = false
                    chPatient3.isChecked = false
                    chPatient4.isChecked = false
                    Log.d("isChecked", subscriberViewModel.ch_patient.value.toString())
                }
            }

            chPatient2.setOnClickListener {
                if (chPatient2.isChecked) {
                    subscriberViewModel.ch_patient.value = chPatient2.text.toString()
                    chPatient.isChecked = false
                    chPatient3.isChecked = false
                    chPatient4.isChecked = false
                    Log.d("isChecked", subscriberViewModel.ch_patient.value.toString())
                }
            }

            chPatient3.setOnClickListener {
                if (chPatient3.isChecked) {
                    subscriberViewModel.ch_patient.value = chPatient3.text.toString()
                    chPatient2.isChecked = false
                    chPatient.isChecked = false
                    chPatient4.isChecked = false
                    Log.d("isChecked", subscriberViewModel.ch_patient.value.toString())
                }
            }

            chPatient4.setOnClickListener {
                if (chPatient4.isChecked) {
                    subscriberViewModel.ch_patient.value = chPatient4.text.toString()
                    chPatient2.isChecked = false
                    chPatient3.isChecked = false
                    chPatient.isChecked = false
                    Log.d("isChecked", subscriberViewModel.ch_patient.value.toString())
                }
            }

                chOperator.setOnClickListener {
                    if (chOperator.isChecked) {
                        subscriberViewModel.ch_operator.value = chOperator.text.toString()
                        chOperator2.setChecked(false)
                        Log.d("isChecked2", subscriberViewModel.ch_operator.value.toString())
                    }
                }

                chOperator2.setOnClickListener {
                    if (chOperator2.isChecked) {
                        subscriberViewModel.ch_operator.value = chOperator2.text.toString()
                        chOperator.setChecked(false)
                        Log.d("isChecked2", subscriberViewModel.ch_operator.value.toString())
                    }
                }

                chBrigade.setOnClickListener {
                    if (chBrigade.isChecked) {
                        subscriberViewModel.ch_brigade.value = chBrigade.text.toString()
                        chBrigade2.isChecked = false
                        Log.d("isChecked3", subscriberViewModel.ch_brigade.value.toString())
                    }
                }

                chBrigade2.setOnClickListener {
                    if (chBrigade2.isChecked) {
                        Log.d("et_times", subscriberViewModel.et_time.value.toString())
                        subscriberViewModel.ch_brigade.value = chBrigade2.text.toString()
                        chBrigade.isChecked = false
                        Log.d("isChecked3", subscriberViewModel.ch_brigade.value.toString())
                    }
                }
            subscriberViewModel.saveTimeFragment()
        }
    }


    override fun onQuantityChange(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            subscriberViewModel.time_fragment.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeTwo(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            subscriberViewModel.time_fragment_two.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeThree(arrayList: ArrayList<String>) {
        for (i in arrayList) {
            subscriberViewModel.time_fragment_three.value = i.toString()
        }
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }
}