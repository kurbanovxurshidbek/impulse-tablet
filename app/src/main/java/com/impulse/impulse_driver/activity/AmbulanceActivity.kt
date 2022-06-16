package com.impulse.impulse_driver.activity

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.adapter.AmbulanceAdapter
import com.impulse.impulse_driver.adapter.MainAdapter
import com.impulse.impulse_driver.databinding.ActivityAmbulanceBinding
import com.impulse.impulse_driver.databinding.ActivitySplashBinding
import com.impulse.impulse_driver.manager.PrefsManager
import com.impulse.impulse_driver.model.PatientInfo
import com.squareup.picasso.Picasso
import java.util.*

/** The application for high volume message **/

class AmbulanceActivity : BaseActivity() {

    lateinit var mediaPlayer : MediaPlayer
    private lateinit var binding: ActivityAmbulanceBinding
    private lateinit var adapter : AmbulanceAdapter
    private var turnOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmbulanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        binding.apply {
            lottieAnimation.setAnimation("sos_animation_plus.json")
            lottieAnimationAmbulance.setAnimation("ambulance_ways.json")
            sosView()

            lottieAnimation.setOnClickListener {
                callMainActivity(this@AmbulanceActivity)
                mediaPlayer.stop()
                saveLoggedState()
                finish()
            }

            callCancel.setOnClickListener {
                mediaPlayer.stop()
                callSplashActivity(this@AmbulanceActivity)
                finish()
            }
        }
        initRecyclerView()
    }

    private fun sosView() {
        mediaPlayer = MediaPlayer.create(this,R.raw.siren)
        mediaPlayer .start()
        mediaPlayer.isLooping = true
    }

    private fun saveLoggedState() {
        turnOn = false
        PrefsManager.getInstance(context)!!.setFirstTime("turnOn",turnOn)
    }

    private fun initRecyclerView() {
        binding.apply {
            rvAmbulance.layoutManager = LinearLayoutManager(context)
            adapter = AmbulanceAdapter(this@AmbulanceActivity)
            rvAmbulance.adapter = adapter
            displaySubscribersList()
        }

    }

    private fun displaySubscribersList() {
        var array = ArrayList<PatientInfo>()
        var subscriber = PatientInfo(
            "Ilhombek Ubaydullayev",
            "Boytepa 4","Yengil",25)
        array.add(subscriber)
        adapter.setList(array)
        adapter.notifyDataSetChanged()

    }
}