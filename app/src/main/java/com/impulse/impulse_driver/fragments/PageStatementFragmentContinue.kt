package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.adapter.PageStatementFragmentContinueAdapter
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.databinding.FragmentStatementPageContinueBinding
import com.impulse.impulse_driver.listener.QuantityListenerFragment
import com.impulse.impulse_driver.model.CheckboxM
import java.util.ArrayList

class PageStatementFragmentContinue : BaseFragment(),QuantityListenerFragment {
    private var _binding: FragmentStatementPageContinueBinding? = null
    private val binding get() = _binding!!

    private var fragment: PageStatementFragmentContinue? = null
    private lateinit var quantityAdapter: PageStatementFragmentContinueAdapter

    fun newInstance(): PageStatementFragmentContinue?{
        if (fragment == null){
            fragment = PageStatementFragmentContinue()
        }
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentStatementPageContinueBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        binding.apply {

        }
        setRecyclerView()
    }
    fun getQuantityData(): ArrayList<CheckboxM> {
        var arrayList : ArrayList<CheckboxM> = ArrayList()
        arrayList.add(CheckboxM("1.Nafas Tizimi",1))
        arrayList.add(CheckboxM("Vezikulyar","Xansirash","Susaygan","Dag`al","Xo`l xrillash",2))
        arrayList.add(CheckboxM("Quruq xrillash","Agonal nafas","Apnoe","Suniy nafasda","Nafas yo`l tiniq",2))
        arrayList.add(CheckboxM("2.Psixikasi",1))
        arrayList.add(CheckboxM("Meyorda","Qo`rquv","Suiqasdga moyil","Sekinlashga","Eyforiyaga xos",3))
        arrayList.add(CheckboxM("Xarakatchan notinch","Agressiv","Paranoyik","Qo`zg`alon","Depressiv",3))
        arrayList.add(CheckboxM("3.Yurak qon-tomir",1))
        arrayList.add(CheckboxM("Yurak tonlari bog`liq","Bradikardiya","Tasixardiya","Asistoliya","Ekstrasistoliya",5))
        arrayList.add(CheckboxM("4.EKG",1))
        arrayList.add(CheckboxM("O`zgarishsiz","Artimiya(xilpillovchi)","Paroksizmal taxikardiya",6))
        arrayList.add(CheckboxM("UKS 5T-sigment ko'tarilishi,tushishi","Qorincha fibrillyasiyasi","Pullsiz aktivlik",6))
        arrayList.add(CheckboxM("5.Teri Qoplami",1))
        arrayList.add(CheckboxM("Toza","Quruq","Rangpar","Sianotik","Toshmali","Shishgan",7))
        arrayList.add(CheckboxM("6.Nevrologiya",1))
        arrayList.add(CheckboxM("Gemiplegiya","Paraplegiya","Tetraplegiya","Babinskiy musbat simptomi","Meningeal musbat simptomlar",8))
        arrayList.add(CheckboxM("7.Ko`z qorachiqlari",1))
        arrayList.add(CheckboxM("O`rtacha","Toraygan","Kengaygan","Anizokoreya","Fotoreaksiya",9))
        arrayList.add(CheckboxM("8.Jarohat soxasi",1))
        arrayList.add(CheckboxM("Bosh","Tana","O`ng qo`l","Chap qo`l","Chap oyoq","O`ng oyoq",10))
        arrayList.add(CheckboxM("9.Glasgo shkalasi",1))
        arrayList.add(CheckboxM("Ko`zi ochiq","So'ralganda","Og`riqqa","Umuman ochmaydi",11))
        return arrayList
    }

    private fun setRecyclerView() {
        binding.apply {
            rvItem.setHasFixedSize(true)
            rvItem.layoutManager = GridLayoutManager(requireContext(),1)
            quantityAdapter = PageStatementFragmentContinueAdapter(requireContext(),getQuantityData(),this@PageStatementFragmentContinue)
            rvItem.adapter = quantityAdapter
        }
    }

    override fun onQuantityChange(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeTwo(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeThree(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeFour(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeFive(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeSix(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeSeven(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeEight(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeNine(arrayList: ArrayList<String>) {
        Toast.makeText(requireContext(),arrayList.toString(),Toast.LENGTH_SHORT).show()
    }
}