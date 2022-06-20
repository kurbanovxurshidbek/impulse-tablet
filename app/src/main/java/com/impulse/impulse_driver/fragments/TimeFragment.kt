package com.impulse.impulse_driver.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentTimeBinding
import com.impulse.impulse_driver.listener.QuantityListener
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
        if (fragment == null) {
            fragment = TimeFragment()
        }
        return fragment
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTimeBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
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
        }
    }

     fun getQuantityData():ArrayList<String>{
         var arrayList : ArrayList<String> = ArrayList()

         arrayList.add("Uyda qoldirildi")
         arrayList.add("Joyida qoldirildi")
         arrayList.add("Tanishildi")
         arrayList.add("Yolg`on")
         arrayList.add("Manzil topilmadi")
         arrayList.add("Boshqa TTYO hizmat")
         arrayList.add("Joyida yo`q")
         arrayList.add("Jabrlangan yo`q")
         arrayList.add("Bekor qilindi №..vaqti")
         return arrayList
    }

    private fun setRecyclerView() {
        binding.apply {
            rvCheckbox.setHasFixedSize(true)
            rvCheckbox.layoutManager = GridLayoutManager(requireContext(),3)
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

                chAmbulanse.setOnClickListener {
                    if (chAmbulanse.isChecked) {
                        subscriberViewModel.chAmbulanse.value = chAmbulanse.text.toString()
                        chAmbulanseB.isChecked = false
                        chAmbulanseA.isChecked = false
                    }
                }

                chAmbulanseA.setOnClickListener {
                    if (chAmbulanseA.isChecked) {
                        subscriberViewModel.chAmbulanse.value = chAmbulanseA.text.toString()
                        chAmbulanseB.isChecked = false
                        chAmbulanse.isChecked = false
                    }
                }

                chAmbulanseB.setOnClickListener {
                    if (chAmbulanseB.isChecked) {
                        subscriberViewModel.chAmbulanse.value = chAmbulanseB.text.toString()
                        chAmbulanse.isChecked = false
                        chAmbulanseA.isChecked = false
                    }
                }
            subscriberViewModel.saveTimeFragment()
        }



        fun onBackPressed(): Boolean {

            return true
        }
    }


    override fun onQuantityChange(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }
}