package com.impulse.impulse_driver.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.MainAdapter
import com.impulse.impulse_driver.adapter.ViewPagerAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.ActivityMainBinding
import com.impulse.impulse_driver.fragments.*
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory
import com.impulse.impulse_driver.service.TimerService
import kotlin.math.roundToInt


/**
 *
The MainActivity stores an ambulance call and patient information
 * **/

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : MainAdapter
    private lateinit var subscriberViewModel: SubscriberViewModel
    private var timerStarted = false
    private lateinit var serviceIntent : Intent
    private var time = 0.0
    var index = 0
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
        serviceIntent = Intent(applicationContext,TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        val dao = MedicineDatabase.getInstance(application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
//        replaceFragment(MapsInfoFragment())
        specilFragments()
        initRecyclerView()
        startTimer()
        binding.apply {
            setupViewPager(viewPager)
            imgNotification.setOnClickListener {
                resetTimer()
                llCircle.setBackgroundResource(R.drawable.circle_timer_sirena)
            }
        }
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
            binding.tvTimer.text = getTimeStringFromDouble(time)
        }

    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60
        if (seconds == 10 && minutes == 0) {
            binding.llCircle.setBackgroundResource(R.drawable.circle_timer_sirena_middle)
        }
        if (seconds == 20 && minutes == 0) {
            binding.llCircle.setBackgroundResource(R.drawable.circle_timer_sirena_full)
        }
        return makeTimeString(hours,minutes,seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d",hour,min,sec)

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
        startService(serviceIntent)
        timerStarted = true
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.tvTimer.text = getTimeStringFromDouble(time)
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        timerStarted = false
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
        var subscriber = PatientInfo()
        array.add(subscriber)
        adapter.setList(array)
        adapter.notifyDataSetChanged()
        saveDatabase(subscriber)

    }

    fun saveDatabase(subscriber: PatientInfo) {
//        subscriberViewModel.saveBaseDatabase()
    }

    /** A special navigation is written here to manage the fragments**/

    private fun specilFragments() {
        binding.apply {
            lnHome.setOnClickListener {
                index = 0
                viewPager.setCurrentItem(index)
            }

            lyClock.setOnClickListener {
                index = 1
                viewPager.setCurrentItem(index)
            }

            llInspection.setOnClickListener {
                index = 2
                viewPager.setCurrentItem(index)
            }

            llDiagnosis.setOnClickListener {
                index = 3
                viewPager.setCurrentItem(index)
            }

            llDrugs.setOnClickListener {
                index = 4
                viewPager.setCurrentItem(index)
            }

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    index = position
                    setFragment(index)
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    }

    private fun setFragment(index: Int) {
        if (index == 0) {
            binding.apply {
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
        }
        else if (index == 1) {
            binding.apply {
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
        }
        else if (index == 2) {
            binding.apply {
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
        }
        else if (index == 3) {
            binding.apply {
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
        }
        else {
            binding.apply {
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

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MapsInfoFragment())
        adapter.addFragment(TimeFragment())
        adapter.addFragment(PageStatementFragment())
        adapter.addFragment(PageStatementFragmentContinue())
        adapter.addFragment(PageMedicineFragment())
        viewPager.setOffscreenPageLimit(3);
        viewPager.adapter = adapter
    }

    private fun initFragments() {
        mapsInfoFragment = MapsInfoFragment().newInstance()!!
        timeFragment = TimeFragment().newInstance()!!
        pageStatementFragment = PageStatementFragment().newInstance()!!
        pageStatementFragmentContinue = PageStatementFragmentContinue().newInstance()!!
        pageMedicineFragment = PageMedicineFragment().newInstance()!!
    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        var fragmentTransition = fragmentManager.beginTransaction()
//        fragmentTransition.replace(R.id.fragmentContainer,fragment)
//        fragmentTransition.commit()
//    }

}