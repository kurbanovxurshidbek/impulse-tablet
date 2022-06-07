package com.impulse.impulse_driver.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ActivityMainBinding
import com.impulse.impulse_driver.fragments.*

/**
 *
The MainActivity stores an ambulance call and patient information
 * **/

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()

    }

    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        replaceFragment(MapsInfoFragment())

        specilFragments()
    }

    /** A special navigation is written here to manage the fragments**/

    private fun specilFragments() {

        binding.apply {

            lnHome.setOnClickListener {
                replaceFragment(MapsInfoFragment())
                imgHome.setImageResource(R.mipmap.home_black)
                lnHome.setBackgroundColor(Color.WHITE)
                lnHome.setBackgroundResource(R.drawable.background_rounded_corners_left)
                tvHome.setTextColor(Color.BLACK)

                imgInspection.setImageResource(R.mipmap.doctor_red)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.mipmap.diagnosic_red)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.mipmap.drugs)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.mipmap.clock_red)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            lyClock.setOnClickListener {
                replaceFragment(TimeFragment())
                imgHome.setImageResource(R.mipmap.home_red3)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.mipmap.doctor_red)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.mipmap.diagnosic_red)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.mipmap.drugs)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.mipmap.clock_black)
                lyClock.setBackgroundColor(Color.WHITE)
                tvClock.setTextColor(Color.BLACK)

            }

            llInspection.setOnClickListener {
                replaceFragment(PageStatementFragment())
                imgInspection.setImageResource(R.mipmap.doctor_black)
                llInspection.setBackgroundColor(Color.WHITE)
                tvInspection.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.mipmap.home_red3)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.mipmap.diagnosic_red)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.mipmap.drugs)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.mipmap.clock_red)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            llDiagnosis.setOnClickListener {
                replaceFragment(PageStatementFragmentContinue())
                imgDiagnosis.setImageResource(R.mipmap.diagnosic_black)
                llDiagnosis.setBackgroundColor(Color.WHITE)
                tvDiagnosis.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.mipmap.home_red3)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.mipmap.doctor_red)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDrugs.setImageResource(R.mipmap.drugs)
                llDrugs.setBackgroundColor(Color.TRANSPARENT)
                tvDrugs.setTextColor(Color.RED)

                imgClock.setImageResource(R.mipmap.clock_red)
                lyClock.setBackgroundColor(Color.TRANSPARENT)
                tvClock.setTextColor(Color.RED)
            }

            llDrugs.setOnClickListener {
                replaceFragment(PageMedicineFragment())
                imgDrugs.setImageResource(R.mipmap.drugs_black)
                llDrugs.setBackgroundColor(Color.WHITE)
                tvDrugs.setTextColor(Color.BLACK)

                imgHome.setImageResource(R.mipmap.home_red3)
                lnHome.setBackgroundColor(Color.TRANSPARENT)
                tvHome.setTextColor(Color.RED)

                imgInspection.setImageResource(R.mipmap.doctor_red)
                llInspection.setBackgroundColor(Color.TRANSPARENT)
                tvInspection.setTextColor(Color.RED)

                imgDiagnosis.setImageResource(R.mipmap.diagnosic_red)
                llDiagnosis.setBackgroundColor(Color.TRANSPARENT)
                tvDiagnosis.setTextColor(Color.RED)

                imgClock.setImageResource(R.mipmap.clock_red)
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