package com.impulse.impulse_driver.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ActivitySignInBinding
import com.impulse.impulse_driver.databinding.ActivitySplashBinding
import com.impulse.impulse_driver.manager.PrefsManager

/**
 * To register the application driver (can only use a special code)
 * **/

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding
    private var isSignIn : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        binding.bOpenActivity.setOnClickListener {
            isSignIn = false
            PrefsManager.getInstance(context)!!.setFirstTime("safe",isSignIn)
            callSplashActivity(this)
            finish()
        }
    }
}