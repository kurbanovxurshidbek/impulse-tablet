package com.impulse.impulse_driver.listener

import android.widget.CheckBox
import com.impulse.impulse_driver.model.CheckboxM
import java.util.ArrayList

public interface QuantityListener {
    fun onQuantityChange(arrayList: ArrayList<String>)
}