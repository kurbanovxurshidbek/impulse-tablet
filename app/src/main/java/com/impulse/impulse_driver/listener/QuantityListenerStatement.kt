package com.impulse.impulse_driver.listener

import android.widget.CheckBox
import com.impulse.impulse_driver.model.CheckboxM
import java.util.ArrayList

/**
 * to temporarily store data in checkboxes
 * **/
public interface QuantityListenerStatement {
    fun onQuantityChange(arrayList: ArrayList<String>)
    fun onQuantityChangeQuartet(arrayList: ArrayList<String>)
}