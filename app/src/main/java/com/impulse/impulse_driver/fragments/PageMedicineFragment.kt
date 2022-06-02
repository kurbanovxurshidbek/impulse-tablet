package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.PageMedicineAdapter
import com.impulse.impulse_driver.databinding.FragmentMedicinePageBinding
import com.impulse.impulse_driver.helper.PostViewModelFactory
import com.impulse.impulse_driver.manager.RoomManager
import com.impulse.impulse_driver.model.Medicine
import com.impulse.impulse_driver.utils.UiStateList
import kotlinx.coroutines.flow.collect


class PageMedicineFragment : Fragment() {
    private var _binding: FragmentMedicinePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardRepository: RoomManager
    private lateinit var viewModel: MedicineViewModel
    private val adapter by lazy { PageMedicineAdapter() }

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


        cardRepository = RoomManager.getInstance(requireContext())
        setupViewModel()
        setupGetAllChatsObserver()
        initViews()
    }

    private fun initViews() {
        binding.apply {
            rvDrugs.adapter = adapter
            adapter.deleteClick = { medicine ->
                viewModel.removeItem(medicine)
                onResume()
            }
        }
        numberPicker()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllChats()
    }

    private fun setupGetAllChatsObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAllChats.collect {
                when(it){
                    is UiStateList.LOADING -> {

                    }
                    is UiStateList.SUCCESS -> {
                        adapter.submitList(it.data)
                    }
                    is UiStateList.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun numberPicker() {
        binding.apply {
            val countries = arrayOf("Uzbekistan",
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
            addButton.setOnClickListener {
                viewModel.saveItem(Medicine(0,"dsa","sda"))
                onResume()
            }
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(cardRepository.postDao())
        )[MedicineViewModel::class.java]
    }
}