package com.impulse.impulse_driver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impulse.impulse_driver.R
import com.impulse.impulse_driver.databinding.*
import com.impulse.impulse_driver.listener.QuantityListenerFragment
import com.impulse.impulse_driver.model.CheckboxM

import java.util.*

/**
 * PageStatementFragmentContinueAdapter to store data in checkboxes and PageStatementFragmentContinue
 * **/

class PageStatementFragmentContinueAdapter(val context: Context, var arrayLists: ArrayList<CheckboxM>, val quantityListener: QuantityListenerFragment): BaseAdapter() {
    private val subscriberList = ArrayList<String>()
    private val subscriberListTwo = ArrayList<String>()
    private val subscriberListThree = ArrayList<String>()
    private val subscriberListFour = ArrayList<String>()
    private val subscriberListFive = ArrayList<String>()
    private val subscriberListSix = ArrayList<String>()
    private val subscriberListSeven = ArrayList<String>()
    private val subscriberListEight = ArrayList<String>()
    private val subscriberListNINE = ArrayList<String>()
    private val subscriberListTeen = ArrayList<String>()

    private var TYPE_ITEM_TWO = 1
    private var  TYPE_ITEM_THREE = 2
    private var TYPE_ITEM_FOUR = 3
    private var TYPE_ITEM_FIVE = 5
    private var TYPE_ITEM_SIX= 6
    private var TYPE_ITEM_SEVEN= 7
    private var TYPE_ITEM_EIGHT= 8
    private var TYPE_ITEM_NINE= 9
    private var TYPE_ITEM_TEEN= 10
    private var TYPE_ITEM_ELEVEN= 11
    private var TYPE_ITEM_TWELVE= 12

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == TYPE_ITEM_TWO) {
            val binding: ItemCheckboxForHeaderBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_for_header, parent, false)
            return CheckboxViewHolder(context, binding)
        }
        else  if (viewType == TYPE_ITEM_FOUR) {
            val binding: ItemCheckboxFragmentBodyBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_fragment_body, parent, false)
            return CheckboxBodyViewHolder(context, binding)
        }else  if (viewType == TYPE_ITEM_FIVE) {
            val binding: ItemCheckboxFragmentBodyBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_fragment_body, parent, false)
            return CheckboxBodyViewHolder(context, binding)
        }else if (viewType == TYPE_ITEM_THREE){
            val binding: ItemCheckboxFragmentBodyBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_fragment_body, parent, false)
            return CheckboxBodyViewHolder(context, binding)
        }else if (viewType == TYPE_ITEM_SIX){
            val binding: ItemFragmentThreeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_three, parent, false)
            return CheckboxBodyViewHs(context, binding)
        }else if (viewType == TYPE_ITEM_SEVEN){
            val binding: ItemFragmentFourBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_four, parent, false)
            return CheckboxBodyViewHFour(context, binding)
        }else if (viewType == TYPE_ITEM_EIGHT){
            val binding: ItemCheckboxFragmentBodyBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_fragment_body, parent, false)
            return CheckboxBodyViewHolder(context, binding)
        }else if (viewType == TYPE_ITEM_NINE){
            val binding: ItemCheckboxFragmentBodyBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_fragment_body, parent, false)
            return CheckboxBodyViewHolder(context, binding)
        }else if (viewType == TYPE_ITEM_TEEN){
            val binding: ItemFragmentFourBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_four, parent, false)
            return CheckboxBodyViewHFour(context, binding)
        }else if (viewType == TYPE_ITEM_ELEVEN){
            val binding: ItemFragmentFiveBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_five, parent, false)
            return CheckboxBodyViewHFive(context, binding)
        }else {
            val binding: ItemFragmentThreeBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_fragment_three, parent, false)
            return CheckboxBodyViewHs(context, binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: CheckboxM = arrayLists[position]
        if (item.position == 1) {
            return TYPE_ITEM_TWO
        }else if (item.position == 3) {
            return TYPE_ITEM_FOUR
        }else if (item.position == 6) {
            return TYPE_ITEM_SIX
        }else if (item.position == 7) {
            return TYPE_ITEM_SEVEN
        }else if (item.position == 8) {
            return TYPE_ITEM_EIGHT
        }else if (item.position == 9) {
            return TYPE_ITEM_NINE
        }else if (item.position == 10) {
            return TYPE_ITEM_TEEN
        }else if (item.position == 11) {
            return TYPE_ITEM_ELEVEN
        }else if (item.position == 12) {
            return TYPE_ITEM_TWELVE
        }
        else {
            return TYPE_ITEM_THREE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: CheckboxM = arrayLists[position]
        if (holder is CheckboxViewHolder) {
            holder.bind(item)
        }else if (holder is CheckboxBodyViewHolder) {
            holder.bind(item)
        }else if (holder is CheckboxBodyViewHs) {
            holder.bind(item)
        }else if (holder is CheckboxBodyViewHFour) {
            holder.bind(item)
        }else if (holder is CheckboxBodyViewHFive){
            holder.bind(item)
        }
    }


    override fun getItemCount(): Int {
        return arrayLists.size
    }


    inner class CheckboxBodyViewHFive(var context: Context, val binding: ItemFragmentFiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxM) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    chItem4.setText(subscriber.ch_four)
                    val item: CheckboxM = arrayLists[position]
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            subscriberListNINE.add(subscriber.ch_one)
                        }else {
                            subscriberListNINE.remove(subscriber.ch_one)
                        }
                        quantityListener.onQuantityChangeNine(subscriberListNINE)
                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            subscriberListNINE.add(subscriber.ch_two)
                        }else {
                            subscriberListNINE.remove(subscriber.ch_two)
                        }
                        quantityListener.onQuantityChangeNine(subscriberListNINE)
                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            subscriberListNINE.add(subscriber.ch_three)
                        }else {
                            subscriberListNINE.remove(subscriber.ch_three)
                        }
                        quantityListener.onQuantityChangeNine(subscriberListNINE)
                    }
                    chItem4.setOnClickListener {
                        if (chItem4.isChecked) {
                            subscriberListNINE.add(subscriber.ch_four)
                        }else {
                            subscriberListNINE.remove(subscriber.ch_four)
                        }
                        quantityListener.onQuantityChangeNine(subscriberListNINE)
                    }
                }
            }
        }
    }


   inner class CheckboxViewHolder(var context: Context, val binding: ItemCheckboxForHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxM) {
            binding.apply {
                tvText.setText(subscriber.mytext)
            }
        }
    }

    inner class CheckboxBodyViewHolder(var context: Context, val binding: ItemCheckboxFragmentBodyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxM) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chOne.setText(subscriber.ch_one)
                    chTwo.setText(subscriber.ch_two)
                    chThree.setText(subscriber.ch_three)
                    chFour.setText(subscriber.ch_four)
                    chFive.setText(subscriber.ch_five)
                    val item: CheckboxM = arrayLists[position]
                    chOne.setOnClickListener {
                        if (chOne.isChecked) {
                            if (item.position == 2) {
                                subscriberList.add(subscriber.ch_one)
                            }else if (item.position == 3) {
                                subscriberListTwo.add(subscriber.ch_one)
                            }else if (item.position == 5){
                                subscriberListThree.add(subscriber.ch_one)
                            }else if (item.position == 8){
                                subscriberListSix.add(subscriber.ch_one)
                            }else if (item.position == 9){
                                subscriberListSeven.add(subscriber.ch_one)
                            }

                        }else {
                            if (item.position == 2) {
                                subscriberList.remove(subscriber.ch_one)
                            }else if (item.position == 5) {
                                subscriberListThree.remove(subscriber.ch_one)
                            }else if (item.position == 8) {
                                subscriberListSix.remove(subscriber.ch_one)
                            }else if (item.position == 9) {
                                subscriberListSeven.remove(subscriber.ch_one)
                            }
                            else {
                                subscriberListTwo.remove(subscriber.ch_one)
                            }
                        }
                        if (item.position == 2) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (item.position == 5) {
                        quantityListener.onQuantityChangeThree(subscriberListThree)
                        }else if (item.position == 8) {
                            quantityListener.onQuantityChangeSix(subscriberListSix)
                        }else if (item.position == 9) {
                            quantityListener.onQuantityChangeSeven(subscriberListSeven)
                        }
                        else {
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }
                    }

                    chTwo.setOnClickListener {
                        if (chTwo.isChecked) {
                            if (item.position == 2) {
                                subscriberList.add(subscriber.ch_two)
                            }else if (item.position == 3) {
                                subscriberListTwo.add(subscriber.ch_two)
                            }else if (item.position == 5){
                                subscriberListThree.add(subscriber.ch_two)
                            }else if (item.position == 8){
                                subscriberListSix.add(subscriber.ch_two)
                            }else if (item.position == 9){
                                subscriberListSeven.add(subscriber.ch_two)
                            }

                        }else {
                            if (item.position == 2) {
                                subscriberList.remove(subscriber.ch_two)
                            }else if (item.position == 5) {
                                subscriberListThree.remove(subscriber.ch_two)
                            }else if (item.position == 8) {
                                subscriberListSix.remove(subscriber.ch_two)
                            }else if (item.position == 9) {
                                subscriberListSeven.remove(subscriber.ch_two)
                            }
                            else {
                                subscriberListTwo.remove(subscriber.ch_two)
                            }

                        }
                        if (item.position == 2) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (item.position == 5) {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }else if (item.position == 8) {
                            quantityListener.onQuantityChangeSix(subscriberListSix)
                        }else if (item.position == 9) {
                            quantityListener.onQuantityChangeSeven(subscriberListSeven)
                        }
                        else {
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }
                    }

                    chThree.setOnClickListener {
                        if (chThree.isChecked) {
                            if (item.position == 2) {
                                subscriberList.add(subscriber.ch_three)
                            }else if (item.position == 3) {
                                subscriberListTwo.add(subscriber.ch_three)
                            }else if (item.position == 5){
                                subscriberListThree.add(subscriber.ch_three)
                            }else if (item.position == 8){
                                subscriberListSix.add(subscriber.ch_three)
                            }else if (item.position == 9){
                                subscriberListSeven.add(subscriber.ch_three)
                            }

                        }else {
                            if (item.position == 2) {
                                subscriberList.remove(subscriber.ch_three)
                            }else if (item.position == 5) {
                                subscriberListThree.remove(subscriber.ch_three)
                            }else if (item.position == 8) {
                                subscriberListSix.remove(subscriber.ch_three)
                            }else if (item.position == 9) {
                                subscriberListSeven.remove(subscriber.ch_three)
                            }
                            else {
                                subscriberListTwo.remove(subscriber.ch_three)
                            }

                        }
                        if (item.position == 2) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (item.position == 5) {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }else if (item.position == 8) {
                            quantityListener.onQuantityChangeSix(subscriberListSix)
                        }else if (item.position == 9) {
                            quantityListener.onQuantityChangeSeven(subscriberListSeven)
                        }
                        else {
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }
                    }

                    chFour.setOnClickListener {
                        if (chFour.isChecked) {
                            if (item.position == 2) {
                                subscriberList.add(subscriber.ch_four)
                            }else if (item.position == 3) {
                                subscriberListTwo.add(subscriber.ch_four)
                            }else if (item.position == 5){
                                subscriberListThree.add(subscriber.ch_four)
                            }else if (item.position == 8){
                                subscriberListSix.add(subscriber.ch_four)
                            }else if (item.position == 9){
                                subscriberListSeven.add(subscriber.ch_four)
                            }

                        }else {
                            if (item.position == 2) {
                                subscriberList.remove(subscriber.ch_four)
                            }else if (item.position == 5) {
                                subscriberListThree.remove(subscriber.ch_four)
                            }else if (item.position == 8) {
                                subscriberListSix.remove(subscriber.ch_four)
                            }else if (item.position == 9) {
                                subscriberListSeven.remove(subscriber.ch_four)
                            }
                            else {
                                subscriberListTwo.remove(subscriber.ch_four)
                            }

                        }
                        if (item.position == 2) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (item.position == 5) {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }else if (item.position == 8) {
                            quantityListener.onQuantityChangeSix(subscriberListSix)
                        }else if (item.position == 9) {
                            quantityListener.onQuantityChangeSeven(subscriberListSeven)
                        }
                        else {
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }
                    }

                    chFive.setOnClickListener {
                        if (chFive.isChecked) {
                            if (item.position == 2) {
                                subscriberList.add(subscriber.ch_five)
                            }else if (item.position == 3) {
                                subscriberListTwo.add(subscriber.ch_five)
                            }else if (item.position == 5){
                                subscriberListThree.add(subscriber.ch_five)
                            }else if (item.position == 8){
                                subscriberListSix.add(subscriber.ch_five)
                            }else if (item.position == 9){
                                subscriberListSeven.add(subscriber.ch_five)
                            }

                        }else {
                            if (item.position == 2) {
                                subscriberList.remove(subscriber.ch_five)
                            }else if (item.position == 5) {
                                subscriberListThree.remove(subscriber.ch_five)
                            }else if (item.position == 8) {
                                subscriberListSix.remove(subscriber.ch_five)
                            }else if (item.position == 9) {
                                subscriberListSeven.remove(subscriber.ch_five)
                            }
                            else {
                                subscriberListTwo.remove(subscriber.ch_five)
                            }

                        }
                        if (item.position == 2) {
                            quantityListener.onQuantityChange(subscriberList)
                        }else if (item.position == 5) {
                            quantityListener.onQuantityChangeThree(subscriberListThree)
                        }else if (item.position == 8) {
                            quantityListener.onQuantityChangeSix(subscriberListSix)
                        }else if (item.position == 9) {
                            quantityListener.onQuantityChangeSeven(subscriberListSeven)
                        }
                        else {
                            quantityListener.onQuantityChangeTwo(subscriberListTwo)
                        }
                    }
                }
            }
        }
    }

    inner class CheckboxBodyViewHs(var context: Context, val binding: ItemFragmentThreeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxM) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    val item: CheckboxM = arrayLists[position]
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            if (item.position == 6) {
                                subscriberListFour.add(subscriber.ch_one)
                            }else if (item.position == 12) {
                                subscriberListTeen.add(subscriber.ch_one)
                            }

                        }else {
                            if (item.position == 6) {
                                subscriberListFour.remove(subscriber.ch_one)
                            }else {
                                subscriberListTeen.remove(subscriber.ch_one)
                            }
                        }
                        if (item.position == 6) {
                            quantityListener.onQuantityChangeFour(subscriberListFour)
                        }else {
                            quantityListener.onQuantityChangeTeen(subscriberListTeen)
                        }
                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            if (item.position == 6) {
                                subscriberListFour.add(subscriber.ch_two)
                            }else if (item.position == 12) {
                                subscriberListTeen.add(subscriber.ch_two)
                            }

                        }else {
                            if (item.position == 6) {
                                subscriberListFour.remove(subscriber.ch_two)
                            }else {
                                subscriberListTeen.remove(subscriber.ch_two)
                            }
                        }
                        if (item.position == 6) {
                            quantityListener.onQuantityChangeFour(subscriberListFour)
                        }else {
                            quantityListener.onQuantityChangeTeen(subscriberListTeen)
                        }
                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            if (item.position == 6) {
                                subscriberListFour.add(subscriber.ch_three)
                            }else if (item.position == 12) {
                                subscriberListTeen.add(subscriber.ch_three)
                            }

                        }else {
                            if (item.position == 6) {
                                subscriberListFour.remove(subscriber.ch_three)
                            }else {
                                subscriberListTeen.remove(subscriber.ch_three)
                            }
                        }
                        if (item.position == 6) {
                            quantityListener.onQuantityChangeFour(subscriberListFour)
                        }else {
                            quantityListener.onQuantityChangeTeen(subscriberListTeen)
                        }
                    }
                }
            }
        }
    }

    inner class CheckboxBodyViewHFour(var context: Context, val binding: ItemFragmentFourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: CheckboxM) {
            binding.apply {
                if (arrayLists!=null && arrayLists.size> 0) {
                    chItem1.setText(subscriber.ch_one)
                    chItem2.setText(subscriber.ch_two)
                    chItem3.setText(subscriber.ch_three)
                    chItem4.setText(subscriber.ch_four)
                    chItem5.setText(subscriber.ch_five)
                    chItem6.setText(subscriber.ch_six)
                    val item: CheckboxM = arrayLists[position]
                    chItem1.setOnClickListener {
                        if (chItem1.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_one)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_one)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_one)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_one)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                    chItem2.setOnClickListener {
                        if (chItem2.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_two)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_two)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_two)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_two)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                    chItem3.setOnClickListener {
                        if (chItem3.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_three)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_three)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_three)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_three)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                    chItem4.setOnClickListener {
                        if (chItem4.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_four)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_four)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_four)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_four)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                    chItem5.setOnClickListener {
                        if (chItem5.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_five)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_five)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_five)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_five)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                    chItem6.setOnClickListener {
                        if (chItem6.isChecked) {
                            if (item.position == 7) {
                                subscriberListFive.add(subscriber.ch_six)
                            }else if (item.position == 10) {
                                subscriberListEight.add(subscriber.ch_six)
                            }

                        }else {
                            if (item.position == 7) {
                                subscriberListFive.remove(subscriber.ch_six)
                            }
                            else {
                                subscriberListEight.remove(subscriber.ch_six)
                            }
                        }
                        if (item.position == 7) {
                            quantityListener.onQuantityChangeFive(subscriberListFive)
                        }
                        else {
                            quantityListener.onQuantityChangeEight(subscriberListEight)
                        }
                    }
                }
            }
        }
    }
}


