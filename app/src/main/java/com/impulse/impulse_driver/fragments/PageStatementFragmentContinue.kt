package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.PageStatementFragmentContinueAdapter
import com.impulse.impulse_driver.adapter.QuantityAdapter
import com.impulse.impulse_driver.databinding.FragmentStatementPageContinueBinding
import com.impulse.impulse_driver.listener.QuantityListenerFragment
import com.impulse.impulse_driver.model.CheckboxM
import com.impulse.impulse_driver.utils.ARG
import java.util.ArrayList

/**
 * PageStatementFragmentContinue mainly to save data from checkboxes to the database
 * **/

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
        arrayList.add(CheckboxM(getString(R.string.str_respiratory),1))
        arrayList.add(CheckboxM(getString(R.string.str_ch_one),getString(R.string.str_ch_two),getString(R.string.str_ch_three),getString(R.string.str_ch_four),getString(R.string.str_ch_five),2))
        arrayList.add(CheckboxM(getString(R.string.str_ch_six),getString(R.string.str_ch_seven),getString(R.string.str_ch_eight),getString(R.string.str_ch_nine),getString(R.string.str_ch_teen),2))
        arrayList.add(CheckboxM(getString(R.string.str_myText),1))
        arrayList.add(CheckboxM(getString(R.string.str_a_one),getString(R.string.str_a_two),getString(R.string.str_a_three),getString(R.string.str_a_four),getString(R.string.str_a_five),3))
        arrayList.add(CheckboxM(getString(R.string.str_b_one),getString(R.string.str_b_two),getString(R.string.str_b_three),getString(R.string.str_b_four),getString(R.string.str_b_five),3))
        arrayList.add(CheckboxM(getString(R.string.str_b_mytext),1))
        arrayList.add(CheckboxM(getString(R.string.str_c_one),getString(R.string.str_c_two),getString(R.string.str_c_three),getString(R.string.str_c_four),getString(R.string.str_c_five),5))
        arrayList.add(CheckboxM(getString(R.string.str_d_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_d_two),getString(R.string.str_d_three),getString(R.string.str_d_four),6))
        arrayList.add(CheckboxM(getString(R.string.str_d_five),getString(R.string.str_d_six),getString(R.string.str_d_seven),6))
        arrayList.add(CheckboxM(getString(R.string.str_e_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_e_two),getString(R.string.str_e_three),getString(R.string.str_e_four),getString(R.string.str_e_five),getString(R.string.str_e_six),getString(R.string.str_e_seven),7))
        arrayList.add(CheckboxM(getString(R.string.str_f_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_f_two),getString(R.string.str_f_three),getString(R.string.str_f_four),getString(R.string.str_f_five),getString(R.string.str_f_six),8))
        arrayList.add(CheckboxM(getString(R.string.str_g_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_g_two),getString(R.string.str_g_three),getString(R.string.str_g_four),getString(R.string.str_g_five),getString(R.string.str_g_six),9))
        arrayList.add(CheckboxM(getString(R.string.str_o_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_o_two),getString(R.string.str_o_three),getString(R.string.str_o_four),getString(R.string.str_o_five),getString(R.string.str_o_six),getString(R.string.str_o_seven),10))
        arrayList.add(CheckboxM(getString(R.string.str_p_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_p_two),getString(R.string.str_p_three),getString(R.string.str_p_four),getString(R.string.str_p_five),11))
        arrayList.add(CheckboxM(getString(R.string.str_r_one),1))
        arrayList.add(CheckboxM(getString(R.string.str_r_two),getString(R.string.str_r_three),getString(R.string.str_r_four),12))
        arrayList.add(CheckboxM(getString(R.string.str_r_five),getString(R.string.str_r_six),getString(R.string.str_r_seven),12))
        arrayList.add(CheckboxM(getString(R.string.str_r_eight),getString(R.string.str_r_nine),getString(R.string.str_r_teen),12))
        arrayList.add(CheckboxM(getString(R.string.str_r_eleven),getString(R.string.str_r_twelve),getString(R.string.str_s_one),12))
        arrayList.add(CheckboxM(getString(R.string.str_s_two),getString(R.string.str_s_three),getString(R.string.str_s_four),12))
        arrayList.add(CheckboxM(getString(R.string.str_s_five),getString(R.string.str_s_six),getString(R.string.str_s_seven),12))
        arrayList.add(CheckboxM(getString(R.string.str_s_eleven),getString(R.string.str_s_twelve),getString(R.string.str_s_eight),12))
        arrayList.add(CheckboxM(getString(R.string.str_s_nine),getString(R.string.str_s_teen),getString(R.string.str_t_one),12))
        arrayList.add(CheckboxM(getString(R.string.str_t_two),getString(R.string.str_t_three),getString(R.string.str_t_four),12))
        arrayList.add(CheckboxM(getString(R.string.str_t_five),getString(R.string.str_t_six),getString(R.string.str_t_seven),12))
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
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_one = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeTwo(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_two = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeThree(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_three = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeFour(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_four = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeFive(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_five = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeSix(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_six = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeSeven(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_seven = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeEight(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_eight = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeNine(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_nine = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }

    override fun onQuantityChangeTeen(arrayList: ArrayList<String>) {
        var strings = ""
        for (i in arrayList) {
            strings += " $i,"
        }
        ARG.statement_continue_teen = strings
        Toast.makeText(requireContext(),strings,Toast.LENGTH_SHORT).show()
    }
}