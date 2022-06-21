package com.impulse.impulse_driver.listener

import android.widget.CheckBox
import com.impulse.impulse_driver.model.CheckboxM
import java.util.ArrayList

public interface QuantityListenerFragment {
    fun onQuantityChange(arrayList: ArrayList<String>)
    fun onQuantityChangeTwo(arrayList: ArrayList<String>)
    fun onQuantityChangeThree(arrayList: ArrayList<String>)
    fun onQuantityChangeFour(arrayList: ArrayList<String>)
    fun onQuantityChangeFive(arrayList: ArrayList<String>)
    fun onQuantityChangeSix(arrayList: ArrayList<String>)
    fun onQuantityChangeSeven(arrayList: ArrayList<String>)
    fun onQuantityChangeEight(arrayList: ArrayList<String>)
    fun onQuantityChangeNine(arrayList: ArrayList<String>)
}