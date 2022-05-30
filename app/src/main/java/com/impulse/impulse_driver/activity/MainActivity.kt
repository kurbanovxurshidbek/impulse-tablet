package com.impulse.impulse_driver.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.impulse.impulse_driver.adapter.ViewPagerAdapter
import com.impulse.impulse_driver.databinding.ActivityMainBinding
import com.impulse.impulse_driver.fragments.PageReferenceFragment
import com.impulse.impulse_driver.fragments.PageStatementFragment
import java.util.*

/**
 *
The MainActivity stores an ambulance call and patient information
 * **/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callLocation()
        initViews()

    }


    /** Location for ambulance with google map **/

    private fun callLocation() {
        val lattitu = "41.325968"
        val longtitu = "69.2348073"
        val adressName ="Navoi Avenue"

        val my_data = String.format(Locale.ENGLISH,
            "http://maps.google.com/maps?daddr=$lattitu,$longtitu$adressName")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(my_data))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private fun initViews() {

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.add(PageStatementFragment(), "Bayonot")
        viewPagerAdapter.add(PageReferenceFragment(), "Tez yordam ma`lumotnomasi")

        binding.viewpager.setAdapter(viewPagerAdapter)
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }

    override fun onBackPressed() {
        if (binding.viewpager.getCurrentItem() == 0) {
            super.onBackPressed()
        } else {
            binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() - 1)
        }
    }
}