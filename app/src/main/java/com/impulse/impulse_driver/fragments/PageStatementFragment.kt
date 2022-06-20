package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.FragmentStatementPageBinding


class PageStatementFragment : BaseFragment() {
    private var _binding: FragmentStatementPageBinding? = null
    private val binding get() = _binding!!

    private var fragment: PageStatementFragment? = null

    fun newInstance(): PageStatementFragment?{
        if (fragment == null){
            fragment = PageStatementFragment()
        }
        return fragment
    }

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

    private fun initViews() {
        binding.apply {
            lottieAnimations.setAnimation("sos_doctor.json")
        }
    }
}