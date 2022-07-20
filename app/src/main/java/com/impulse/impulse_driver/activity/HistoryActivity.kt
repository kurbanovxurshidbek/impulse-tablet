package com.impulse.impulse_driver.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.AmbulanceAdapter
import com.impulse.impulse_driver.adapter.HistoryAdapter
import com.impulse.impulse_driver.adapter.PageMedicineAdapter
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.database.entity.BaseMedicine
import com.impulse.impulse_driver.database.entity.Medicine
import com.impulse.impulse_driver.databinding.ActivityAmbulanceBinding
import com.impulse.impulse_driver.databinding.ActivityHistoryBinding
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory
import java.util.ArrayList

/**
 * HistoryActivity Storing Patient Information
 * **/

class HistoryActivity : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter : HistoryAdapter
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
//        obtaining information from (dao) database
        val dao = MedicineDatabase.getInstance(this.application).subscriberDao
        val repository = MedicineRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        initRecyclerView()
    }

//    get information from adapter to recyclerview
    private fun initRecyclerView() {
        binding.apply {
            rvHistory.layoutManager = GridLayoutManager(context, 1)
            val animation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
            rvHistory.layoutAnimation = animation
            adapter = HistoryAdapter(this@HistoryActivity,{ seletedItem: BaseMedicine ->listItemClicked(seletedItem)})
            displaySubscribersList()
            rvHistory.adapter = adapter
        }
    }

//    delivering information from the viewmodel to the adapter
    private fun displaySubscribersList() {
        subscriberViewModel.subscribers_base.observe(this, Observer {
            Log.d("MyTag",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

//    status when a list is pressed
    private fun listItemClicked(subscriber: BaseMedicine) {
        var intent = Intent(this,SharedActivity::class.java)
        intent.putExtra("member", subscriber)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }
}