package com.impulse.impulse_driver.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ActivitySplashBinding
import com.impulse.impulse_driver.manager.PrefsManager

/**
* In SplashActivity driver can visit to SignInActivity or MainActivity
**/

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private val TAG = SplashActivity::class.java.toString()
    private lateinit var binding: ActivitySplashBinding
    private lateinit var lottieAnimationView: LottieAnimationView
    private var medicalCall : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        lottieAnimationView = findViewById(R.id.lottie_animation)
        lottieAnimationView.setAnimation("ambulance.json")
        if (PrefsManager.getInstance(context)!!.isFirstTime("Yes")) {
            medicalCall = true
        }
        countDownTimer()

    }

    private fun countDownTimer() {
        object : CountDownTimer(2200, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {

                if (PrefsManager.getInstance(context)!!.isFirstTime("safe")) {
                    callSignInActivity(this@SplashActivity)

                }
                else if (medicalCall){
                    if (PrefsManager.getInstance(context)!!.isFirstTime("turnOn")) {
                        callAmbulanceActivity(this@SplashActivity)
                        finish()
                }else {
                        callMainActivity(this@SplashActivity)
                        finish()
                }
                }else {
                    Toast.makeText(this@SplashActivity,R.string.info_call,Toast.LENGTH_LONG).show()
                }

//                callMainActivity(this@SplashActivity)
//                finish()
                binding.apply {
                    openHistory.setOnClickListener {
                        callHistoryActivity(this@SplashActivity)
                        finish()

                    }
                }

            }
        }.start()
    }

}