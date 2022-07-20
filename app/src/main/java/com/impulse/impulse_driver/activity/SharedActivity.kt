package com.impulse.impulse_driver.activity

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.SharedAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.databinding.ActivitySharedBinding
import com.impulse.impulse_driver.model.SharedInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

class SharedActivity : BaseActivity() {

    private lateinit var binding: ActivitySharedBinding
    private lateinit var adapter : SharedAdapter
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val dao = MedicineDatabase.getInstance(this.application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.apply {
            val member = intent.getSerializableExtra("member") as BaseMedicine
            tName.setText(member.fullName)
            tAge.setText(member.age)
            tWeight.setText(member.weight.toString())
            tHeight.setText(member.height.toString())
            tBloodtype.setText(member.bloodGroup)
//            fragmentDrawer.callStatus.setText("${getString(R.string.str_call_statuss)} : ${member.callStatus} ")
            Glide.with(context).load(member.patientImg).placeholder(R.drawable.user)
                .error(R.drawable.cancel).into(pfImage)
            lottieAnimationClick.setAnimation("clickB.json")

            var arrayList : ArrayList<BaseMedicine> = ArrayList()
            arrayList.add(member)
            initRecyclerView()
            val members = prepareMemberList(member)
            refreshAdapter(members)
        }
        openLogoxml()
    }

    private fun initRecyclerView() {
        binding.apply {
            fragmentDrawer.rvShared.layoutManager = GridLayoutManager(context, 1)
        }
    }


//    to save all data
    private fun prepareMemberList(member: BaseMedicine): List<SharedInfo> {
        val members = ArrayList<SharedInfo>()
        members.add(SharedInfo("${getString(R.string.str_call_statuss)} : ${member.callStatus}"))
        members.add(SharedInfo("${getString(R.string.str_number_card)} : ${member.cardNumber}"))
        members.add(SharedInfo("${getString(R.string.str_brigade_card)} : ${member.cardNumberSecond}"))
        members.add(SharedInfo("${getString(R.string.str_patient_ttyo)} : ${member.ch_patient}"))
        members.add(SharedInfo("${getString(R.string.str_call_category)} : ${member.ch_operator}"))
        members.add(SharedInfo("${getString(R.string.str_brigade)} : ${member.ch_brigade}"))
        members.add(SharedInfo("${getString(R.string.str_region)} : ${member.addressName}"))
        members.add(SharedInfo("${getString(R.string.str_street)} : ${member.street}"))
        members.add(SharedInfo("${getString(R.string.phone_number)} : ${member.phoneNumber}"))
        members.add(SharedInfo("${getString(R.string.str_current_date)} : ${member.currentTimes}"))
        members.add(SharedInfo("${getString(R.string.str_date)} : ${member.currentDate}"))
        members.add(SharedInfo("${getString(R.string.str_group)} : ${member.groupN}"))
        members.add(SharedInfo("${getString(R.string.str_given_time)} : ${member.et_time}"))
        members.add(SharedInfo("${getString(R.string.str_reached_the_place)} : ${member.et_timeP}"))
        members.add(SharedInfo("${getString(R.string.str_taken_hospital)} : ${member.et_timeS}"))
        members.add(SharedInfo("${getString(R.string.str_finished_time)} : ${member.et_timeFinish}"))
        members.add(SharedInfo("( ${getString(R.string.str_patient_fate)} (${getString(R.string.str_first_aid)}) ) : ${member.time_fragment}"))
        members.add(SharedInfo("( ${getString(R.string.str_patient_fate)} (${getString(R.string.str_death_toreturn)}) ) : ${member.time_fragment_two}"))
        members.add(SharedInfo("( ${getString(R.string.str_patient_fate)} (${getString(R.string.str_participation_with)}) ) : ${member.time_fragment_three}"))
        members.add(SharedInfo("${getString(R.string.str_hospital_name)} : ${member.chAmbulanse_name}"))
        members.add(SharedInfo("${getString(R.string.str_doctor)} : ${member.doctor}"))
        members.add(SharedInfo("${getString(R.string.str_signature)} : ${member.signature}"))
        members.add(SharedInfo("${getString(R.string.str_accepted_person)} : ${member.signaturePerson}"))
        members.add(SharedInfo("${getString(R.string.str_blood_pressure_base)} -> ${getString(R.string.str_first)}  : ${member.indicators1}"))
        members.add(SharedInfo("${getString(R.string.str_blood_pressure_base)} -> ${getString(R.string.str_after_help)}  : ${member.indicators7}"))
        members.add(SharedInfo("${getString(R.string.str_blood_pressure_base)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators13}"))
        members.add(SharedInfo("${getString(R.string.str_puls)} -> ${getString(R.string.str_first)}  : ${member.indicators2}"))
        members.add(SharedInfo("${getString(R.string.str_puls)} -> ${getString(R.string.str_after_help)}  : ${member.indicators8}"))
        members.add(SharedInfo("${getString(R.string.str_puls)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators14}"))
        members.add(SharedInfo("${getString(R.string.str_breath_count)} -> ${getString(R.string.str_first)}  : ${member.indicators3}"))
        members.add(SharedInfo("${getString(R.string.str_breath_count)} -> ${getString(R.string.str_after_help)}  : ${member.indicators9}"))
        members.add(SharedInfo("${getString(R.string.str_breath_count)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators15}"))
        members.add(SharedInfo("${getString(R.string.str_saturatsion)} -> ${getString(R.string.str_first)}  : ${member.indicators4}"))
        members.add(SharedInfo("${getString(R.string.str_saturatsion)} -> ${getString(R.string.str_after_help)}  : ${member.indicators10}"))
        members.add(SharedInfo("${getString(R.string.str_saturatsion)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators16}"))
        members.add(SharedInfo("${getString(R.string.str_temperature)} -> ${getString(R.string.str_first)}  : ${member.indicators5}"))
        members.add(SharedInfo("${getString(R.string.str_temperature)} -> ${getString(R.string.str_after_help)}  : ${member.indicators11}"))
        members.add(SharedInfo("${getString(R.string.str_temperature)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators17}"))
        members.add(SharedInfo("${getString(R.string.str_blood_glucose)} -> ${getString(R.string.str_first)}  : ${member.indicators6}"))
        members.add(SharedInfo("${getString(R.string.str_blood_glucose)} -> ${getString(R.string.str_after_help)}  : ${member.indicators12}"))
        members.add(SharedInfo("${getString(R.string.str_blood_glucose)} -> ${getString(R.string.str_came_hospital)}  : ${member.indicators18}"))
        members.add(SharedInfo("${getString(R.string.phone_number)} : ${member.phoneNumber}"))
        members.add(SharedInfo("${getString(R.string.phone_number)} : ${member.phoneNumber}"))
        members.add(SharedInfo("${getString(R.string.phone_number)} : ${member.phoneNumber}"))
        return members
    }

    private fun refreshAdapter(members: List<SharedInfo>) {
        binding.apply {
            adapter = SharedAdapter(this@SharedActivity,members)
            fragmentDrawer.rvShared.adapter = adapter
        }
    }

//    data from below
    private fun openLogoxml() {
        val bottomSheetBehavior = BottomSheetBehavior.from<RelativeLayout>(binding.fragmentDrawer.relativeSheet)
        binding.lottieAnimationClick.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }
}