package com.impulse.impulse_driver.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ActivityMainBinding
import com.impulse.impulse_driver.fragments.*
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

    @SuppressLint("ResourceAsColor")
    private fun initViews() {

        replaceFragment(PageReferenceFragment())


        specilFragments()
    }

    /** A special navigation is written here to manage the fragments**/

    private fun specilFragments() {

        binding.apply {

            lnHome.setOnClickListener {
                replaceFragment(PageReferenceFragment())
                imgHome.setImageResource(R.drawable.ic_home_black)
                lnHome.setBackgroundColor(Color.WHITE)
                lnHome.setBackgroundResource(R.drawable.background_rounded_corners_left)
                tvHome.setTextColor(Color.BLACK)

                imgInspection.setImageResource(R.drawable.doctors)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.drawable.diagnosis)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.drawable.medicine)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.drawable.clock)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            lyClock.setOnClickListener {
                replaceFragment(PageStatementTimeFragment())
                imgHome.setImageResource(R.drawable.ic_baseline_home_24)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.drawable.doctors)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.drawable.diagnosis)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.drawable.medicine)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.drawable.clockred)
                lyClock.setBackgroundColor(Color.WHITE)
                tvClock.setTextColor(Color.BLACK)

            }

            llInspection.setOnClickListener {
                replaceFragment(PageStatementFragment())
                imgInspection.setImageResource(R.drawable.doctorsb)
                llInspection.setBackgroundColor(Color.WHITE)
                tvInspection.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.drawable.ic_baseline_home_24)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.drawable.diagnosis)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.drawable.medicine)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.drawable.clock)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            llDiagnosis.setOnClickListener {
                replaceFragment(PageStatementFragmentContinue())
                imgDiagnosis.setImageResource(R.drawable.diagnosisb)
                llDiagnosis.setBackgroundColor(Color.WHITE)
                tvDiagnosis.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.drawable.ic_baseline_home_24)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.drawable.doctors)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.drawable.medicine)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.drawable.clock)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            llDrugs.setOnClickListener {
                replaceFragment(PageMedicineFragment())
                imgDrugs.setImageResource(R.drawable.medicineb)
                llDrugs.setBackgroundColor(Color.WHITE)
                tvDrugs.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.drawable.ic_baseline_home_24)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.drawable.doctors)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.drawable.diagnosis)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgClock.setImageResource(R.drawable.clock)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        var fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment)
        fragmentTransition.commit()
    }

}