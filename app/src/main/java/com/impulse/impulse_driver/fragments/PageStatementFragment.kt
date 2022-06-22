package com.impulse.impulse_driver.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.PageStatementAdapter
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentStatementPageBinding
import com.impulse.impulse_driver.listener.QuantityListenerStatement
import com.impulse.impulse_driver.model.CheckboxStatement
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory
import java.util.ArrayList


class PageStatementFragment : BaseFragment(),QuantityListenerStatement {
    private var _binding: FragmentStatementPageBinding? = null
    private val binding get() = _binding!!
    private var fragment: PageStatementFragment? = null
    private lateinit var quantityAdapter: PageStatementAdapter
    private lateinit var subscriberViewModel: SubscriberViewModel

    fun newInstance(): PageStatementFragment?{
        if (fragment == null){
            fragment = PageStatementFragment()
        }
        return fragment
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
        val dao = MedicineDatabase.getInstance(requireActivity().application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.apply {
            myViewModel = subscriberViewModel
            lifecycleOwner = requireActivity()
            lottieAnimations.setAnimation("sos_doctor.json")
//            subscriberViewModel.statementFragment()
        }
        setRecyclerView()
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
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeQuartet(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }
}