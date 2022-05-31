package com.impulse.impulse_driver.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.adapter.PageMedicineAdapter
import com.impulse.impulse_driver.database.AppDatabase
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.databinding.FragmentMedicinePageBinding
import com.impulse.impulse_driver.model.Medicine


class PageMedicineFragment : Fragment() {
    private var _binding: FragmentMedicinePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardRepository: AppDatabase
    private lateinit var adapter: PageMedicineAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMedicinePageBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        cardRepository = AppDatabase(requireActivity().application)
        binding.apply {

            rvDrugs.layoutManager = GridLayoutManager(requireContext(),1)
            val data = cardRepository.postDao!!.getCards()
            saveCardToServer(1,data)
            adapter = PageMedicineAdapter(requireContext(),data as ArrayList<Medicine>)
            rvDrugs.adapter = adapter
        }
        numberPicker()
    }

    private fun addDrugs() {
        binding.apply {
            getAllChats()

            val data = cardRepository.postDao!!.getCards()
            var index = 0
            saveCardToServer(index, data)
        }
    }

    private fun saveCardToServer(index: Int, data: List<Medicine>) {
        var i = index
        val adapter = PageMedicineAdapter(requireContext(), data as ArrayList<Medicine>)
        binding.apply {

            rvDrugs.adapter = adapter
        }
    }

    private fun getAllChats(): ArrayList<Medicine> {
        val drugs: ArrayList<Medicine> = ArrayList<Medicine>()
        binding.apply {
            var drugss = Medicine(drugs.size.toLong(),autoCompleteTextview2.text.toString(),numberPicker.value.toString())
            cardRepository.postDao!!.saveCard(drugss)
            drugs.add(drugss)
            autoCompleteTextview2.text.clear()
        }
        return drugs
    }

    private fun numberPicker() {
        binding.apply {
            val countries = arrayOf("Uzbekistan","USA","Tashkent","Namangan","LasVegas","New_York","Qazag'istan","Qo'qon","Turkiya","Liviya","Ukraina")
            val adapterArray = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,countries)
            autoCompleteTextview2.setAdapter(adapterArray)
            autoCompleteTextview2.threshold = 1
            numberPicker.minValue = 0
            numberPicker.maxValue = 20
            addButton.setOnClickListener {
                addDrugs()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadCardsFromDatabase() {
        adapter.list.clear()
        adapter.list.addAll(cardRepository.postDao!!.getCards())
        adapter.notifyDataSetChanged()
    }
}