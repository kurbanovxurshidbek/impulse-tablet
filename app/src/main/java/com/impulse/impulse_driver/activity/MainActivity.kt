package com.impulse.impulse_driver.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.events.Subscriber
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.MainAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine
import com.impulse.impulse_driver.databinding.ActivityMainBinding
import com.impulse.impulse_driver.fragments.*
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

/**
 *
The MainActivity stores an ambulance call and patient information
 * **/

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : MainAdapter
    private lateinit var subscriberViewModel: SubscriberViewModel

    // fragments
    private lateinit var mapsInfoFragment: MapsInfoFragment
    private lateinit var timeFragment: TimeFragment
    private lateinit var pageStatementFragment: PageStatementFragment
    private lateinit var pageStatementFragmentContinue: PageStatementFragmentContinue
    private lateinit var pageMedicineFragment: PageMedicineFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initFragments()
        initViews()

    }

    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        val dao = MedicineDatabase.getInstance(application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.apply {

        }
        replaceFragment(MapsInfoFragment())
        specilFragments()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.apply {
            pInfoRv.layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(this@MainActivity)
            pInfoRv.adapter = adapter
            displaySubscribersList()
        }

    }

    private fun displaySubscribersList() {
        var array = ArrayList<PatientInfo>()
        var subscriber = PatientInfo("https://images.unsplash.com/photo-1655207162062-766b99476e6a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80",
            "Ilhombek Ubaydullayev",
            "Boytepa 4","Yengil",25,64,178,123,2)
        array.add(subscriber)
        adapter.setList(array)
        adapter.notifyDataSetChanged()
        saveDatabase(subscriber)

    }

    fun saveDatabase(subscriber: PatientInfo) {
        subscriberViewModel.fullName.value = subscriber.fullName
        subscriberViewModel.callStatus.value = subscriber.callStatus
        subscriberViewModel.saveBaseDatabase()
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

    private fun initFragments() {
        mapsInfoFragment = MapsInfoFragment().newInstance()!!
        timeFragment = TimeFragment().newInstance()!!
        pageStatementFragment = PageStatementFragment().newInstance()!!
        pageStatementFragmentContinue = PageStatementFragmentContinue().newInstance()!!
        pageMedicineFragment = PageMedicineFragment().newInstance()!!
    }

//    fun replaceFragment(fragment: Fragment) {
//        val backStateName = fragment.javaClass.name
//        val manager = supportFragmentManager
//        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
//        if (!fragmentPopped) {
//            val ft = manager.beginTransaction()
//            ft.replace(R.id.fragmentContainer, fragment)
//            ft.addToBackStack(backStateName)
//            ft.commit()
//        }
//    }
//
//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount == 1)
//            finish()
//        else if (supportFragmentManager.fragments.any { it is TimeFragment }) {
//            if (timeFragment.onBackPressed())
//                super.onBackPressed()
//        } else
//            super.onBackPressed()
//    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        var fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment)
        fragmentTransition.commit()
    }

}