package com.impulse.impulse_driver.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.impulse.impulse_driver.activity.SplashActivity
import com.impulse.impulse_driver.adapter.PageMedicineAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.FragmentMedicinePageBinding
import com.impulse.impulse_driver.manager.PrefsManager
import com.impulse.impulse_driver.model.Medicine
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory


class PageMedicineFragment : BaseFragment() {

    private var _binding: FragmentMedicinePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter : PageMedicineAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMedicinePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        numberPicker()
    }

    private fun initViews() {
        val dao = MedicineDatabase.getInstance(requireActivity().application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.apply {
            myViewModel = subscriberViewModel
            lifecycleOwner = requireActivity()
            initRecyclerView()

            subscriberViewModel.message.observe(requireActivity(), Observer {
                it.getContentIfNotHandler()?.let {
                    Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
                }
            })

            done.setOnClickListener {
                PrefsManager.getInstance(requireContext())!!.setFirstTime("Yes",false)
                val intent = Intent(requireActivity(), SplashActivity::class.java)
                startActivity(intent)
            }
        }

        myEnter()
        hideKeyboard()
    }

    private fun initRecyclerView() {
        binding.rvDrugs.layoutManager = LinearLayoutManager(requireContext())
        adapter = PageMedicineAdapter({ seletedItem: Medicine ->listItemClicked(seletedItem)})
        binding.rvDrugs.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(requireActivity(), Observer {
            Log.d("MyTag",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()

        })
    }

    private fun listItemClicked(subscriber: Medicine) {
//        Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_SHORT).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }



    private fun numberPicker() {
        binding.apply {
            val countries = arrayOf("GLISEVIT tabletkalari N30",
                "USA",
                "Tashkent",
                "Namangan",
                "LasVegas",
                "New_York",
                "Qazag'istan",
                "Qo'qon",
                "Turkiya",
                "Liviya",
                "Ukraina")
            val adapterArray = ArrayAdapter<String>(requireContext(),
                android.R.layout.simple_list_item_1,
                countries)
            autoCompleteTextview2.setAdapter(adapterArray)
            autoCompleteTextview2.threshold = 1

            numberPicker.minValue = 1
            numberPicker.maxValue = 50
            numberPicker.wrapSelectorWheel = true

        }
    }

    //Handle enter button
    fun myEnter() {
        binding.apply {
            autoCompleteTextview2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    hideKeyboard()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    //Hide our keyboard
    fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val hide = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(view.windowToken,0)
        }
        //else
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}