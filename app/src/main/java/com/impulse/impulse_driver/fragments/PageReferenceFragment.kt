package com.impulse.impulse_driver.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.databinding.FragmentReferencePageBinding

class PageReferenceFragment : Fragment(){

    private var _binding: FragmentReferencePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReferencePageBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        binding.apply {
            var tv_call = _binding!!.tvCall.text.toString()
            openContact.setOnClickListener {
                openCallContact(tv_call)
            }
        }
    }

    private fun openCallContact(tvCall: String) {
        val callIntent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+Uri.encode(tvCall)))
        startActivity(callIntent)
    }
}