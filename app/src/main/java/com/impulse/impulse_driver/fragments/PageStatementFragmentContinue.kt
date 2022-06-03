package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.FragmentReferencePageBinding
import com.impulse.impulse_driver.databinding.FragmentStatementPageBinding
import com.impulse.impulse_driver.databinding.FragmentStatementPageContinueBinding

class PageStatementFragmentContinue : BaseFragment() {
    private var _binding: FragmentStatementPageContinueBinding? = null
    private val binding get() = _binding!!
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

    }
}