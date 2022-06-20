package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.database.MedicineDatabase
import com.impulse.impulse_driver.database.MedicineRepository
import com.impulse.impulse_driver.databinding.ItemCheckboxThreeBinding
import com.impulse.impulse_driver.databinding.ItemMainHeaderBinding
import com.impulse.impulse_driver.listener.QuantityListener
import com.impulse.impulse_driver.model.CheckboxM
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

import java.util.*

class QuantityAdapter(val context: Context,var arrayLists: ArrayList<String>,val quantityListener: QuantityListener): BaseAdapter() {
    private val subscriberList = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCheckboxThreeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_three, parent, false)
        return CheckboxViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: String = arrayLists[position]
        val itemViewHolder: CheckboxViewHolder = holder as CheckboxViewHolder
        itemViewHolder.bind(item)
    }


    override fun getItemCount(): Int {
        return arrayLists.size
    }


   inner class CheckboxViewHolder(var context: Context, val binding: ItemCheckboxThreeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: String) {
            binding.apply {
                var item = checkboxItem
                if (arrayLists!=null && arrayLists.size> 0) {
                    checkboxItem.setText(subscriber)
                    checkboxItem.setOnClickListener {
                        if (checkboxItem.isChecked) {
                            subscriberList.add(subscriber)
                        }else {
                            subscriberList.remove(subscriber)
                        }
                        quantityListener.onQuantityChange(subscriberList)
                    }
                }
            }
        }
    }
}
