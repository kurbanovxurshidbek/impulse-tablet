package com.impulse.impulse_driver.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ActivityAmbulanceBinding
import com.impulse.impulse_driver.databinding.ActivitySplashBinding
import com.impulse.impulse_driver.manager.PrefsManager

// The application for high volume message

class AmbulanceActivity : BaseActivity() {

    private lateinit var lottieAnimationView: LottieAnimationView
    lateinit var mediaPlayer : MediaPlayer
    private lateinit var binding: ActivityAmbulanceBinding
    private var turnOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmbulanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        lottieAnimationView = findViewById(R.id.lottie_animation)
        lottieAnimationView.setAnimation("medical_cross_sos_emergency.json")
        sosView()
        lottieAnimationView.setOnClickListener {
            callMainActivity(this)
            mediaPlayer.stop()
            saveLoggedState()
            finish()
        }
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
}