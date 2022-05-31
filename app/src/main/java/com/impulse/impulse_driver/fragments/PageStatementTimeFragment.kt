package com.impulse.impulse_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.FragmentStatementPageBinding
import com.impulse.impulse_driver.databinding.FragmentStatementTimePageBinding


class PageStatementTimeFragment : Fragment() {
    private var _binding: FragmentStatementTimePageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentStatementTimePageBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        binding.apply {

        }
    }
}