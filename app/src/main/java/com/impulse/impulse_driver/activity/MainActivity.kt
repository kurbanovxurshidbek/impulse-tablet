package com.impulse.impulse_driver.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.impulse.impulse_driver.R
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {

        val lattitu = "41.325968"
        val longtitu = "69.2348073"
        val adressName ="Navoi Avenue"

        val my_data = String.format(Locale.ENGLISH,
            "http://maps.google.com/maps?daddr=$lattitu,$longtitu$adressName")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(my_data))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}