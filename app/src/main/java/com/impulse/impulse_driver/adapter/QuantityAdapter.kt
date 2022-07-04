package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.ItemCheckboxThreeBinding
import com.impulse.impulse_driver.databinding.ItemHeaderTimeBinding
import com.impulse.impulse_driver.listener.QuantityListener
import com.impulse.impulse_driver.model.CheckboxTime

import java.util.*

class QuantityAdapter(val context: Context,var arrayLists: ArrayList<CheckboxTime>,val quantityListener: QuantityListener): BaseAdapter() {
    private val subscriberList = ArrayList<String>()
    private val subscriberListTwo = ArrayList<String>()
    private val subscriberListThree = ArrayList<String>()
    private var TYPE_ITEM_ONE = 0
    private var TYPE_ITEM_TWO = 1
    private var  TYPE_ITEM_THREE = 2
    private var  TYPE_ITEM_FOUR = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == TYPE_ITEM_ONE) {
            val binding: ItemHeaderTimeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_header_time, parent, false)
            return HeaderViewHolders(context, binding)
        }else if (viewType == TYPE_ITEM_THREE) {
            val binding: ItemCheckboxThreeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_three, parent, false)
            return CheckboxViewHolder(context, binding)
        }else if (viewType == TYPE_ITEM_FOUR) {
            val binding: ItemCheckboxThreeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_three, parent, false)
            return CheckboxViewHolder(context, binding)
        }else {
            val binding: ItemCheckboxThreeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_three, parent, false)
            return CheckboxViewHolder(context, binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: CheckboxTime = arrayLists[position]
        if (holder is HeaderViewHolders) {
            holder.bind(item)
        }else if (holder is CheckboxViewHolder) {
            holder.bind(item)
        }
    }


    override fun getItemCount(): Int {
        return arrayLists.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: CheckboxTime = arrayLists[position]
        if (item.position == 0) {
            return TYPE_ITEM_ONE
        }else if (item.position == 2) {
            return TYPE_ITEM_THREE
        }else if (item.position == 3) {
            return TYPE_ITEM_FOUR
        }else {
            return TYPE_ITEM_TWO
        }
    }

    inner class HeaderViewHolders(var context: Context, val binding: ItemHeaderTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxTime) {
            binding.apply {
                tvText.setText(subscriber.mytext)
            }
        }
    }



    inner class CheckboxViewHolder(var context: Context, val binding: ItemCheckboxThreeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxTime) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            if (subscriber.position == 1) {
                                subscriberList.add(subscriber.ch_one)
                                chItem2.isChecked = false
                                chItem3.isChecked = false
                                subscriberList.remove(subscriber.ch_two)
                                subscriberList.remove(subscriber.ch_three)
                            }else if (subscriber.position == 2) {
                                subscriberListTwo.add(subscriber.ch_one)
                                chItem2.isChecked = false
                                chItem3.isChecked = false
                                subscriberListTwo.remove(subscriber.ch_two)
                                subscriberListTwo.remove(subscriber.ch_three)
                            }else if (subscriber.position == 3) {
                                subscriberListThree.add(subscriber.ch_one)
                                chItem2.isChecked = false
                                chItem3.isChecked = false
                                subscriberListThree.remove(subscriber.ch_two)
                                subscriberListThree.remove(subscriber.ch_three)
                            }

                        }else {
                            if (subscriber.position == 1) {
                                subscriberList.remove(subscriber.ch_one)
                            }else if (subscriber.position == 2){
                                subscriberListTwo.remove(subscriber.ch_one)
                            }else{
                                subscriberListThree.remove(subscriber.ch_one)
                            }
                        }
                        if (subscriber.position == 1) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (subscriber.position == 2){
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }else {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }

                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            if (subscriber.position == 1) {
                                subscriberList.add(subscriber.ch_two)
                                chItem1.isChecked = false
                                chItem3.isChecked = false
                                subscriberList.remove(subscriber.ch_one)
                                subscriberList.remove(subscriber.ch_three)
                            }else if (subscriber.position == 2) {
                                subscriberListTwo.add(subscriber.ch_two)
                                chItem1.isChecked = false
                                chItem3.isChecked = false
                                subscriberListTwo.remove(subscriber.ch_one)
                                subscriberListTwo.remove(subscriber.ch_three)
                            }else if (subscriber.position == 3) {
                                subscriberListThree.add(subscriber.ch_two)
                                chItem1.isChecked = false
                                chItem3.isChecked = false
                                subscriberListThree.remove(subscriber.ch_one)
                                subscriberListThree.remove(subscriber.ch_three)
                            }

                        }else {
                            if (subscriber.position == 1) {
                                subscriberList.remove(subscriber.ch_two)
                            }else if (subscriber.position == 2){
                                subscriberListTwo.remove(subscriber.ch_two)
                            }else{
                                subscriberListThree.remove(subscriber.ch_two)
                            }
                        }
                        if (subscriber.position == 1) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (subscriber.position == 2){
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }else {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }

                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            if (subscriber.position == 1) {
                                subscriberList.add(subscriber.ch_three)
                                chItem1.isChecked = false
                                chItem2.isChecked = false
                                subscriberList.remove(subscriber.ch_one)
                                subscriberList.remove(subscriber.ch_two)
                            }else if (subscriber.position == 2) {
                                subscriberListTwo.add(subscriber.ch_three)
                                chItem1.isChecked = false
                                chItem2.isChecked = false
                                subscriberListTwo.remove(subscriber.ch_one)
                                subscriberListTwo.remove(subscriber.ch_two)
                            }else if (subscriber.position == 3) {
                                subscriberListThree.add(subscriber.ch_three)
                                chItem1.isChecked = false
                                chItem2.isChecked = false
                                subscriberListThree.remove(subscriber.ch_one)
                                subscriberListThree.remove(subscriber.ch_two)
                            }

                        }else {
                            if (subscriber.position == 1) {
                                subscriberList.remove(subscriber.ch_three)
                            }else if (subscriber.position == 2){
                                subscriberListTwo.remove(subscriber.ch_three)
                            }else{
                                subscriberListThree.remove(subscriber.ch_three)
                            }
                        }
                        if (subscriber.position == 1) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (subscriber.position == 2){
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }else {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }

                    }
                }
            }
        }
    }
}
