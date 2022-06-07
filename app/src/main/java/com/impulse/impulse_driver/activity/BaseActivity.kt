package com.impulse.impulse_driver.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
/ BaseActivity To manage all activities
/ **/

open class BaseActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

    fun callMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun callAmbulanceActivity(context: Context) {
        val intent = Intent(this, AmbulanceActivity::class.java)
        startActivity(intent)
    }

    fun callSignInActivity(context: Context) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun callSplashActivity(context: Context) {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }

}