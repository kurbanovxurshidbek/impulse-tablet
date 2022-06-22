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
import com.impulse.impulse_driver.databinding.*
import com.impulse.impulse_driver.listener.QuantityListener
import com.impulse.impulse_driver.listener.QuantityListenerStatement
import com.impulse.impulse_driver.model.CheckboxM
import com.impulse.impulse_driver.model.CheckboxStatement
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.presenter.SubscriberViewModel
import com.impulse.impulse_driver.presenter.SubscriberViewModelFactory

import java.util.*

class PageStatementAdapter(val context: Context, var arrayLists: ArrayList<CheckboxStatement>, val quantityListener: QuantityListenerStatement): BaseAdapter() {

    private var TYPE_ITEM_ONE = 0
    private var TYPE_ITEM_TWO = 1
    private var  TYPE_ITEM_THREE = 2
    private val subscriberList = ArrayList<String>()
    private val subscriberListQuartet = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == 0) {
            val binding: ItemStatementHeaderBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_statement_header, parent, false)
            return HeaderViewHolder(context, binding)
        }else if (viewType == 1){
            val binding: ItemFragmentStatementBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_statement, parent, false)
            return CheckboxViewHolder(context, binding)
        }
        else {
            val binding: ItemQuartetStatementBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_quartet_statement, parent, false)
            return QuartetViewHolder(context, binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: CheckboxStatement = arrayLists[position]
        if (holder is HeaderViewHolder) {
            holder.bind(item)
        }else if (holder is CheckboxViewHolder) {
            holder.bind(item)
        }else if (holder is QuartetViewHolder) {
            holder.bind(item)
        }
    }


    override fun getItemCount(): Int {
        return arrayLists.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: CheckboxStatement = arrayLists[position]
        if (item.position == 0) {
            return TYPE_ITEM_ONE
        }else if (item.position == 2) {
            return TYPE_ITEM_THREE
        }else {
            return TYPE_ITEM_TWO
        }
    }


    inner class HeaderViewHolder(var context: Context, val binding: ItemStatementHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxStatement) {
            binding.apply {
                tvText.setText(subscriber.mytext)
            }
        }
    }

    inner class QuartetViewHolder(var context: Context, val binding: ItemQuartetStatementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxStatement) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    chItem4.setText(subscriber.ch_four)
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            subscriberListQuartet.add(subscriber.ch_one)
                        }else {
                            subscriberListQuartet.remove(subscriber.ch_one)
                        }
                        quantityListener.onQuantityChangeQuartet(subscriberListQuartet)
                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            subscriberListQuartet.add(subscriber.ch_two)
                        }else {
                            subscriberListQuartet.remove(subscriber.ch_two)
                        }
                        quantityListener.onQuantityChangeQuartet(subscriberListQuartet)
                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            subscriberListQuartet.add(subscriber.ch_three)
                        }else {
                            subscriberListQuartet.remove(subscriber.ch_three)
                        }
                        quantityListener.onQuantityChangeQuartet(subscriberListQuartet)
                    }
                    chItem4.setOnClickListener {
                        if (chItem4.isChecked) {
                            subscriberListQuartet.add(subscriber.ch_four)
                        }else {
                            subscriberListQuartet.remove(subscriber.ch_four)
                        }
                        quantityListener.onQuantityChangeQuartet(subscriberListQuartet)
                    }
                }
            }
        }
    }

    inner class CheckboxViewHolder(var context: Context, val binding: ItemFragmentStatementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxStatement) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            subscriberList.add(subscriber.ch_one)
                        }else {
                            subscriberList.remove(subscriber.ch_one)
                        }
                        quantityListener.onQuantityChange(subscriberList)
                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            subscriberList.add(subscriber.ch_two)
                        }else {
                            subscriberList.remove(subscriber.ch_two)
                        }
                        quantityListener.onQuantityChange(subscriberList)
                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            subscriberList.add(subscriber.ch_three)
                        }else {
                            subscriberList.remove(subscriber.ch_three)
                        }
                        quantityListener.onQuantityChange(subscriberList)
                    }
                }
            }
        }
    }
}
