package com.impulse.impulse_driver.listener

import android.widget.CheckBox
import com.impulse.impulse_driver.model.CheckboxM
import java.util.ArrayList

public interface QuantityListenerStatement {
    fun onQuantityChange(arrayList: ArrayList<String>)
    fun onQuantityChangeQuartet(arrayList: ArrayList<String>)
}