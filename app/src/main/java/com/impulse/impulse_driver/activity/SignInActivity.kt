package com.impulse.impulse_driver.activity

import android.content.Context
import android.graphics.Color
import android.hardware.input.InputManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
        myEnter()
        hideKeyboard()
        binding.apply {
            bOpenActivity.setOnClickListener {
                isSignIn = false
                PrefsManager.getInstance(context)!!.setFirstTime("safe",isSignIn)
                bOpenActivity.setTextColor(Color.WHITE)
                callSplashActivity(this@SignInActivity)
                finish()
            }
        }
    }

    //handle enter button
    private fun myEnter() {
        binding.apply {
            etDriverID.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    hideKeyboard()
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    //Hide register keyboard
    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
        //else
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}