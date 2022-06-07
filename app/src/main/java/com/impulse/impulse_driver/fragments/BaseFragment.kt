package com.impulse.impulse_driver.fragments

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.util.*

open class BaseFragment : Fragment() {

    /** Location for ambulance with google map **/

    fun callLocation() {
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